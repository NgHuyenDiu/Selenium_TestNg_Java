package testcases;

import base.BaseSetup;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;

public class CartTC extends BaseSetup {
    WebDriver driver;
    HomePage homePage;
    CartPage cartPage;
    LoginPage loginPage;

    @BeforeClass
    public void setUp(){
       driver = getDriver();
       homePage = new HomePage(driver);
       cartPage = new CartPage(driver);
       loginPage = new LoginPage(driver);

    }
    @Test()
    public void login(){

        loginPage.login("standard_user","secret_sauce");
    }
    @Test()
    public void removeProductInCart(){
        // add product to cart
        homePage.clickProduct(3);
        homePage.clickProduct(2);

        // go to cart page
        homePage.clickBtnCart();

        //remove product
        cartPage.clickRemoveProductInCart(0,homePage.getListProductNameTemp() );

        // verify
        boolean isTrueProduct1 = cartPage.verifyProductInCartTrue(homePage.getListProductNameTemp());
        boolean isNumberOfProductTrue1 = homePage.verifyNumberOfProductInCartTrue(1);
        if(isNumberOfProductTrue1 == true && isTrueProduct1 == true){
            Assert.assertTrue(true, "Remove first product in shopping cart true");
            Reporter.log("Remove first product in shopping cart true");

        }else{
            Assert.assertFalse(true, "Remove first product in shopping cart fail");
            Reporter.log("Remove first product in shopping cart fail");
        }

        //continue remove another product
        cartPage.clickRemoveProductInCart(0,homePage.getListProductNameTemp());

        // verify
        boolean isTrueProduct0 = cartPage.verifyProductInCartTrue(homePage.getListProductNameTemp());
        boolean isNumberOfProductTrue0 = homePage.verifyNumberOfProductInCartTrue(0);
        if(isNumberOfProductTrue0 == true && isTrueProduct0 == true){
            Assert.assertTrue(true, "Remove second product in shopping cart true");
            Reporter.log("Remove second product in shopping cart true");

        }else{
            Assert.assertFalse(true, "Remove second product in shopping cart fail");
            Reporter.log("Remove second product in shopping cart fail");
        }
    }

}
