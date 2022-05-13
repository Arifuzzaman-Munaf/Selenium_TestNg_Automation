package bs23.com.pages.components;

import bs23.com.base.BasePage;
import bs23.com.pages.CartPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class ProductFunctions extends BasePage {

    @FindBy(css = "a[title='View cart']")
    @CacheLookup private WebElement viewCartLink ;


    public ProductFunctions(WebDriver driver) {
        super(driver);
    }

    /*
    this method takes dynamic string data
    as product name and generates a locator
    */

    private By addToCartButtonElement(String productName){
        return By.xpath(
                "//a[@aria-label='Add “"+productName+"” to your cart']"
                    );
    }


    //   this method find the add to cart button and execute click operation
    @Step
    public ProductFunctions clickAddToCartButton(String productName){
        By addToCartButton = addToCartButtonElement(productName);
        super.click(addToCartButton);
        return this;
    }


    //   this method navigates to cart page
    @Step
    public CartPage clickViewCartLink(){
        super.click(viewCartLink);
        return new CartPage(driver);
    }
}
