package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class CheckoutOverview {
    WebDriver driver;
    By titleBy = By.xpath("//span[@class='title']");
    By btnFinishBy = By.id("finish");
    private final String TITLE_OVERVIEW = "Checkout: Overview";
    private final String TITLE_COMPLETE = "Checkout: Complete!";
    public CheckoutOverview(WebDriver driver){
        this.driver = driver;
    }
    public boolean verifyCheckoutOverviewPage(){
        String title = driver.findElement(titleBy).getText();
        String expectedTitle = TITLE_OVERVIEW;
        return title.equals(expectedTitle);
    }

    public void clickBtnFinish(){
        WebElement btnFinish = driver.findElement(btnFinishBy);
        btnFinish.click();
    }

    public boolean VerifyCompletePage(){
        String title = driver.findElement(titleBy).getText();
        String expectedTitle = TITLE_COMPLETE ;
        return title.equals(expectedTitle);
    }

}
