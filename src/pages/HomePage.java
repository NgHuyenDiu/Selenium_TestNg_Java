package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class HomePage {
    private WebDriver driver;

    private By productListBy = By.xpath("//div[@class = 'inventory_container']/div");
    private By productNameBy = By.xpath(".//div[@class = 'inventory_item_name']");
    private By btnaddToCardBy = By.xpath(".//button[contains(@id, 'add-to-cart')]");
    private By btnRemoveBy = By.xpath(".//button[contains(@name,'remove')]");

    private By numberOfProductBy = By.xpath("//span[@class = 'shopping_cart_badge']");
    private By btnCartBy = By.xpath("//a[@class = 'shopping_cart_link']");

    private List<String> listProductNameTemp = new ArrayList<>();

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public List<String> getListProductNameTemp() {
        return listProductNameTemp;
    }

    public WebElement getProductByIndex(int index) {
        WebElement products = driver.findElement(productListBy);
        WebElement product = products.findElement(By.xpath("./div[" + index + "]"));
        return product;
    }

    public void removeProductToListNameTemp(WebElement product) {
        String productName = product.findElement(productNameBy).getText();
        listProductNameTemp.remove(String.valueOf(productName));
    }

    public void addProductToListNameTemp(WebElement product) {
        String productName = product.findElement(productNameBy).getText();
        listProductNameTemp.add(productName);

    }

    public void clickProduct(int index) {
        WebElement product = getProductByIndex(index);
        addProductToListNameTemp(product);
        WebElement btnAddTocart = product.findElement(btnaddToCardBy);
        btnAddTocart.click();
    }

    public boolean verifyWebElement(By xpath) {
        try {
            driver.findElement(xpath);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public int getNumberOfProductInShopingCart() {
        boolean verify = verifyWebElement(numberOfProductBy);
        if (verify == true) {
            WebElement numberOfProduct = driver.findElement(numberOfProductBy);
            int number = Integer.parseInt(numberOfProduct.getText());
            return number;
        } else {
            return 0;
        }

    }

    public boolean verifyNumberOfProductInCartTrue(int total) {
        int numberOfProduct = getNumberOfProductInShopingCart();
        if (numberOfProduct == total) {
            return true;
        } else {
            return false;
        }
    }

    public void clickBtnCart() {
        WebElement btnCart = driver.findElement(btnCartBy);
        if (btnCart.isDisplayed()) {
            btnCart.click();
        }
    }


    public void clickRemoveProduct(int index) {

        WebElement product = getProductByIndex(index);
        removeProductToListNameTemp(product);
        WebElement btnRemove = product.findElement(btnRemoveBy);
        if (btnRemove.isDisplayed()) {
            btnRemove.click();
        }

    }

}
