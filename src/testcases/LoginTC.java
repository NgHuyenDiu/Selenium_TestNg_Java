package testcases;

import base.BaseSetup;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTC extends BaseSetup {
    private WebDriver driver;
    public LoginPage loginPage;
    @BeforeClass
    public void setUp(){
        driver = getDriver();

    }
    @Test
    public void loginToPage(){
        System.out.println(driver);
        loginPage = new LoginPage(driver);
        loginPage.login("standard_user","secret_sauce");
    }
}
