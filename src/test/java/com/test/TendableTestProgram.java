package com.test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class TendableTestProgram {

	private WebDriver driver;
	private String baseUrl = "https://www.tendable.com";

	@BeforeClass
	public void openBrowser() {
		WebDriverManager.chromedriver().clearDriverCache().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get(baseUrl);
	}

	@Test
	public void verifyErrorMessage() throws InterruptedException {
		String[] menuItems = { "Our Story", "Our Solution", "Why Tendable?", "About Us" };

		for (String item : menuItems) {
			WebElement menu = driver.findElement(By.linkText(item));
			Assert.assertTrue(menu.isDisplayed());
			menu.click();
			Thread.sleep(2000);

			WebElement demoButton = driver.findElement(By.xpath("//a[text()='Request A Demo']"));
			Assert.assertTrue(demoButton.isDisplayed(), "'Request a Demo' button is not present on " + item + " page.");
		}

		driver.findElement(By.linkText("Contact Us")).click();
		Thread.sleep(7000);
		WebElement marketingOption1 = driver.findElement(By.xpath("//*[text()='Our Locations']"));
		scrollHorizontalInsideWindow(marketingOption1);
		Thread.sleep(2000);
		WebElement marketingOption = driver.findElement(By.xpath("(//button[text()='Contact'])[1]"));
		marketingOption.click();

		// Fill form excluding Message field
		driver.findElement(By.xpath("(//*[@id='form-input-fullName'])[1]")).sendKeys("Sushil Doiphode");
		driver.findElement(By.xpath("(//*[@name='organisationName'])[1]")).sendKeys("ABCD");
		driver.findElement(By.xpath("(//*[@name='cellPhone'])[1]")).sendKeys("1234567890");
		driver.findElement(By.xpath("(//*[@name='email'])[1]")).sendKeys("abc@gmail.com");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//form[@id='contactMarketingPipedrive-163701']//input[@id='form-input-consentAgreed-0']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//button[@name='form_page_submit'])[1]")).click();
		Thread.sleep(2000);

		// Check for error message
		WebElement errorMessage = driver.findElement(By.xpath("//*[contains(text(), 'Sorry, there was an error submitting the form. Please try again.')]"));
		Assert.assertEquals(errorMessage.getText(), "Sorry, there was an error submitting the form. Please try again.");

		WebElement errorMessage2 = driver.findElement(By.xpath("//*[contains(text(), 'This field is required')]"));
		Assert.assertEquals(errorMessage2.getText(), "This field is required");
	}
	
	
	public void  scrollHorizontalInsideWindow(WebElement element){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

}
