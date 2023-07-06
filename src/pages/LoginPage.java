package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;
    private By usernameBy = By.id("user-name");
    private By passwordBy = By.id("password");
    private By btnLoginBy = By.id("login-button");

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public String getLoginTitle(){
        String title = driver.getTitle();
        return title;
    }

    public boolean verifyLoginTitle(String title){
        String expectedTitle = "Swag Labs";
        return getLoginTitle().equals(expectedTitle);
    }

    public void enterUsername(String input){
        WebElement txtUsername = driver.findElement(usernameBy);
        if(txtUsername.isDisplayed()){
            txtUsername.sendKeys(input);
        }
    }
    public void enterPassword(String input){
        WebElement txtPassword = driver.findElement(passwordBy);
        if(txtPassword.isDisplayed()){
            txtPassword.sendKeys(input);
        }
    }

    public void clickLoginButton(){
        WebElement submit = driver.findElement(btnLoginBy);
        if(submit.isDisplayed()){
            submit.click();
        }
    }

    public void login(String txtUsername, String txtPassword){
        enterUsername(txtUsername);
        enterPassword(txtPassword);
        clickLoginButton();
    }
}
