package testcases;

import base.BaseSetup;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;

public class HomeTC extends BaseSetup {
    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private CartPage cartPage;

    @BeforeClass
    public void setUp(){

        driver = getDriver();
        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);

    }
    @Test()
    public void login(){
        loginPage = new LoginPage(driver);
        loginPage.login("standard_user","secret_sauce");
    }
    @Test()
    public void addProductToCart(){
        // Add product to cart
        homePage.clickProduct(3);
        homePage.clickProduct(2);

        // Verify number of products
        boolean isTrueNumberOfProduct = homePage.verifyNumberOfProductInCartTrue(2);

        // go to cart page to verify that add true product
        homePage.clickBtnCart();
        boolean isTrueListProduct = cartPage.verifyProductInCartTrue(homePage.getListProductNameTemp());

        // if true number of products, true list product -> successful
        if(isTrueListProduct == true && isTrueNumberOfProduct == true){
            Assert.assertTrue(true, "Add product successfully");

        }else{
           Assert.assertTrue(false, "Add product fail");
        }

        // go back to home page to continue next test
        goBack();
    }

    @Test()
    public void removeProduct(){

        homePage.clickRemoveProduct(3);
        // verify
        Assert.assertTrue(homePage.verifyNumberOfProductInCartTrue(1), "Fail remove the first product");

        // continue remove another product
        homePage.clickRemoveProduct(2);
        Assert.assertTrue(homePage.verifyNumberOfProductInCartTrue(0), "Fail remove the second product");

    }

}
