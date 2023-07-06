package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.sql.Driver;
import java.time.Duration;

public class BaseSetup {
    private WebDriver driver;
    static String driverPath = "resources\\driver\\";

    public WebDriver getDriver(){
        return driver;
    }
    public void goBack(){
        driver.navigate().back();
    }
    private static WebDriver initChromeDriver(String url){
        System.out.println("Launching Chrome browser...");
        System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to(url);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        return driver;
    }

    private static WebDriver initFirefoxDriver(String url){
        System.out.println("Launching Firefox browser...");
        System.setProperty("webdriver.firefox.driver",driverPath + "geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.navigate().to(url);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        return driver;
    }

    private void setDriver(String browserType, String url){
        switch (browserType){
            case "chrome":{
                driver = initChromeDriver(url);
                break;
            }
            case "firefox":{
                driver = initFirefoxDriver(url);
                break;
            }
            default:{
                System.out.println("Browser: "+ browserType +" is invalid");
                driver = initChromeDriver(url);
            }
        }
    }
    // ham initializeTestBaseSetup se chay truoc het khi class nay duoc goi
    @Parameters({"browserType", "url"})
    @BeforeClass
    public void initializeTestBaseSetup(String browsertype, String url){
        try{
            // Khoi chay browser
            setDriver(browsertype, url);
        }catch (Exception e){
            System.out.println(" Error: "+ e.getStackTrace());
        }
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }
}
