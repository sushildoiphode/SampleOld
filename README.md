# Tendable Website Test Automation

## Overview
This project contains automated tests for the Tendable website. The tests validate the accessibility of top-level menus, the presence of the "Request a Demo" button on these pages, and form validation on the "Contact Us" page.

## Tests Implemented
1. **Top Level Menus**: Confirms that the Home, Our Story, Our Solution, and Why Tendable menus are accessible and that the "Request a Demo" button is present.
2. **Contact Us Form Validation**: Tests that submitting the form without the "Message" field triggers an error.

## Setup
1. Clone the repository.
2. Ensure Java and Maven are installed.
3. Update the `chromedriver` path in the `TendableTest.java` file.

## How to Run
1. Navigate to the project directory.
2. Run the tests using Maven: 
   ```bash
   mvn test
