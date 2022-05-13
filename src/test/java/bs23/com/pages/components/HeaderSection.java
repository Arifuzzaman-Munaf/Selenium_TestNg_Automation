package bs23.com.pages.components;

import bs23.com.base.BasePage;
import bs23.com.pages.StorePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HeaderSection extends BasePage {

    @FindBy(how = How.CSS, using ="#menu-item-1227 > a" )
    @CacheLookup private WebElement store_link;

    public HeaderSection(WebDriver driver) {
        super(driver);
    }

    public StorePage clickStoreMenuLink(){
        super.click(store_link);
        return new StorePage(driver);
    }

}
