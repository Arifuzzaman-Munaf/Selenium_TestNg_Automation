package bs23.com.pages;

import bs23.com.base.BasePage;
import bs23.com.pages.components.ProductFunctions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class StorePage extends BasePage {

    private ProductFunctions productFunctions;


    @FindBy(how = How.XPATH, using = "//input[@type=\"search\"]")
    @CacheLookup private WebElement searchBox;

    @FindBy(css = "button[value='Search']" )
    @CacheLookup private WebElement searchButton;

    private final By title = By.cssSelector(".woocommerce-products-header__title.page-title");


//    constructor for store page
    public StorePage(WebDriver driver) {
        super(driver);
        productFunctions = new ProductFunctions(driver);
    }

    @Step
    public ProductFunctions getProductFunctions() {
        return productFunctions;
    }

    @Step
    public StorePage load(){
        super.loadUrl("/store");
        return this;
    }



//    this method executes the search operation with keyword
    public StorePage search(String text) {
        WebElement element = super.getByVisibility(searchBox);
        element.sendKeys(text);
        super.click(searchButton);
        return this;
    }


////    this method returns the title of the searched item
//    public String getTitle(){
//        return super.getText(title);
//    }




//   this method validate the page title
//   whether the title is okay or not
    public void validateTitle(String title) {
        super.validateText(this.title, title);
    }
}
