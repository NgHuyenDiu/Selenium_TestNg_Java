package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class CartPage {
    WebDriver driver;
    private By ListProductInCartBy = By.xpath("//div[@class='cart_list']/div[@class ='cart_item']");
    private By productNameBy = By.xpath(".//div[@class = 'inventory_item_name']");
    private By btnRemoveInCartBy = By.xpath(".//button[text() ='Remove']");
    private By ListNameProductInCartBy = By.xpath("//div[@class='cart_list']/div[@class ='cart_item']//div[@class='inventory_item_name']");
    private By btnCheckoutBy = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickBtnCheckout() {
        WebElement btnCheckout = driver.findElement(btnCheckoutBy);
        if (btnCheckout.isDisplayed()) {
            btnCheckout.click();
        }
    }

    public boolean verifyProductInCartTrue(List<String> listProductNameTemp) {
        boolean pass = true;
        List<WebElement> productsInCart = driver.findElements(ListNameProductInCartBy);
        if (productsInCart.size() != listProductNameTemp.size()) {
            return false;
        } else {
            for (int index = 0; index < productsInCart.size(); index++) {
                if (productsInCart.get(index).getText().equals(listProductNameTemp.get(index)) == false) {
                    pass = false;
                }
            }

        }

        return pass;
    }

    public WebElement getProductInCartByIndex(int index) {
        List<WebElement> productsInCart = driver.findElements(ListProductInCartBy);
        WebElement productInCart = productsInCart.get(index);
        return productInCart;
    }

    public void removeProductToListNameTemp(WebElement product, List<String> listProductNameTemp) {
        String name = product.findElement(productNameBy).getText();
        listProductNameTemp.remove(String.valueOf(name));
    }

    public void clickRemoveProductInCart(int index, List<String> listProductNameTemp) {
        WebElement product = getProductInCartByIndex(index);
        removeProductToListNameTemp(product, listProductNameTemp);
        WebElement btnRemove = product.findElement(btnRemoveInCartBy);
        btnRemove.click();
    }
}
