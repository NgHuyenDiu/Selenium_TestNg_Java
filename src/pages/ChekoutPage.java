package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ChekoutPage {
    WebDriver driver;
    By firstNameBy = By.id("first-name");
    By lastNameBy = By.id("last-name");
    By zipBy = By.id("postal-code");
    By btnContinueBy = By.id("continue");
    By errorBy = By.xpath("//h3[@data-test='error']");
    final String FIRST_NAME_ERROR = "Error: First Name is required";
    final String LAST_NAME_ERROR = "Error: Last Name is required";
    final String ZIP_CODE_ERROR = "Error: Postal Code is required";

    public ChekoutPage(WebDriver driver){
        this.driver = driver;
    }

    public void enterFirstName(String input){
        WebElement firstName = driver.findElement(firstNameBy);
        firstName.sendKeys(Keys.CONTROL+ "a");
        firstName.sendKeys(Keys.BACK_SPACE);
        firstName.sendKeys(input);
    }
    public void enterLastName(String input){
        WebElement lastName = driver.findElement(lastNameBy);
        lastName.sendKeys(Keys.CONTROL+ "a");
        lastName.sendKeys(Keys.BACK_SPACE);
        lastName.sendKeys(input);
    }

    public void enterZipCode(String input){
        WebElement zipcode = driver.findElement(zipBy);
        zipcode.sendKeys(Keys.CONTROL+ "a");
        zipcode.sendKeys(Keys.BACK_SPACE);
        zipcode.sendKeys(input);
    }

    public void clickBtnContinue(){
        WebElement btnContinue = driver.findElement(btnContinueBy);
        btnContinue.click();
    }

    public void enterCheckOut(String firstName, String lastName, String zipcode){
        enterFirstName(firstName);
        enterLastName(lastName);
        enterZipCode(zipcode);

    }

    public String getErrorText(){
        WebElement errorMes = driver.findElement(errorBy);
        return errorMes.getText();
    }

    public boolean isFirstNameRequire()
    {
        return getErrorText().equals(FIRST_NAME_ERROR);
    }
    public boolean isLastNameRequire()
    {
        return getErrorText().equals(LAST_NAME_ERROR);
    }
    public boolean isZipCodeRequire()
    {
        return getErrorText().equals(ZIP_CODE_ERROR);
    }

}
