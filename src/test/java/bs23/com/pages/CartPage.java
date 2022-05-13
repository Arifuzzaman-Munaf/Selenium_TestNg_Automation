package bs23.com.pages;

import bs23.com.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CartPage extends BasePage {


    private By productName      = By.cssSelector("td[class='product-name'] a");

    @FindBy(how = How.CSS, using = ".wc-proceed-to-checkout > a")
    @CacheLookup private WebElement checkOutButton;

    public CartPage(WebDriver driver) {
        super(driver);
    }


//    this method returns the name of
//    element from product list
//    public String getProductName(){
//        return super.getText(productName);
//    }


//  this method helps to click on check out button
    public CheckOutPage clickCheckOutButton(){
        super.click(checkOutButton);
        return new CheckOutPage(driver);
    }

    @Step
    public void validateProduct(String product) {

        super.validateText(productName, product);
    }
}
