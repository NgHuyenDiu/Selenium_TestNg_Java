package testcases;

import base.BaseSetup;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.*;

public class CheckoutTC extends BaseSetup {
    WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    CartPage cartPage;
    ChekoutPage chekoutPage;
    CheckoutOverview checkoutOverview;

    @BeforeClass
    public void setUp() {
        driver = getDriver();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);
        chekoutPage = new ChekoutPage(driver);
        checkoutOverview = new CheckoutOverview(driver);
    }
    @Test
    public void login(){
        loginPage.login("standard_user","secret_sauce");
    }

    @Test
    public void preCondition(){
        // add product to cart
        homePage.clickProduct(2);
        homePage.clickProduct(3);
        // go to cart page
        homePage.clickBtnCart();
        // go to checkout page
        cartPage.clickBtnCheckout();
    }
    @Test
    public void verifyThatUserNeedEnterAllRequireField(){
        // Enter info
        chekoutPage.enterCheckOut("", "nguyen", "1234");
        // Click continue button
        chekoutPage.clickBtnContinue();
        Assert.assertTrue( chekoutPage.isFirstNameRequire(), "Firstname is not empty");
        chekoutPage.enterCheckOut("", "", "");
        // Enter info
        chekoutPage.enterCheckOut("diu", "", "1234");
        // Click continue button
        chekoutPage.clickBtnContinue();
        Assert.assertTrue( chekoutPage.isLastNameRequire(), "Lastname is not empty");

        // Enter info
        chekoutPage.enterCheckOut("Diu", "nguyen", "");
        // Click continue button
        chekoutPage.clickBtnContinue();
        Assert.assertTrue( chekoutPage.isZipCodeRequire(), "Zip code is not empty");
    }
    @Test
    public void VerifyThatUserCanCheckoutSuccessfully(){

        // Enter info
        chekoutPage.enterCheckOut("diu", "nguyen", "1234");
        // Click continue button
        chekoutPage.clickBtnContinue();

        // verify go to overview page
        boolean isNavigateOverviewPage = checkoutOverview.verifyCheckoutOverviewPage();

        if(isNavigateOverviewPage == true){
            // go to complete page
            checkoutOverview.clickBtnFinish();
            // verify
            Assert.assertTrue(checkoutOverview.VerifyCompletePage(),"Checkout fail"); ;

        }else{
           Assert.assertTrue(false, "Go to overview checkout page fail");
        }

    }


}
