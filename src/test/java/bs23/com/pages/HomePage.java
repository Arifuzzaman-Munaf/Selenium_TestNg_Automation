package bs23.com.pages;

import bs23.com.base.BasePage;
import bs23.com.pages.components.HeaderSection;
import bs23.com.pages.components.ProductFunctions;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private HeaderSection headerSection;
    private ProductFunctions productFunctions;

    public HeaderSection getHeaderSection() {
        return headerSection;
    }



    public HomePage(WebDriver driver) {
        super(driver);
        headerSection = new HeaderSection(driver);
        productFunctions = new ProductFunctions(driver);
    }


    @Step
    public ProductFunctions getProductFunctions() {
        return productFunctions;
    }

    public HomePage load(){
        loadUrl("/");
        return this;
    }

}
