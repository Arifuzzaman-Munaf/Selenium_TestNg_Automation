package bs23.com.pages;

import bs23.com.base.BasePage;
import bs23.com.objects.BillingAddress;
import bs23.com.pages.components.Overlays;
import bs23.com.utilities.JavaScriptUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import java.util.List;


public class CheckOutPage extends BasePage {

    private Overlays overlays;
    @FindBy(how = How.ID, using = "billing_first_name" )
    @CacheLookup private WebElement firstNameBox;

    @FindBy(how = How.ID, using = "billing_last_name" )
    @CacheLookup private WebElement lastNameBox;

    @FindBy(how = How.ID, using = "select2-billing_country-container" )
    @CacheLookup private WebElement billingCountryDropdown;

    @FindBy(how = How.ID, using = "billing_address_1" )
    @CacheLookup private WebElement billingAddressBox ;

    @FindBy(how = How.ID, using = "billing_city" )
    @CacheLookup private WebElement billingCityBox ;

    @FindBy(how = How.ID, using = "select2-billing_state-container" )
    @CacheLookup private WebElement billingStateDropdown ;

    @FindBy(how = How.ID, using = "billing_postcode" )
    @CacheLookup private WebElement billingZipBox  ;

    @FindBy(how = How.ID, using = "billing_email" )
    @CacheLookup private WebElement billingEmailBox  ;

    @FindBy(how = How.ID, using = "place_order" )
    @CacheLookup private WebElement placeOrderButton ;


    private final By confirmationMessage = By.cssSelector(".woocommerce-order >p");

    @FindBy(how = How.CLASS_NAME, using = "showlogin" )
    @CacheLookup private WebElement loginLink  ;

    @FindBy(how = How.ID, using = "username" )
    @CacheLookup private WebElement userEmailField ;

    @FindBy(how = How.ID, using = "password" )
    @CacheLookup private WebElement userPasswordField  ;

    @FindBy(how = How.NAME, using = "login" )
    @CacheLookup private WebElement loginButton ;

    @FindBy(how = How.CSS, using = ".blockUI.blockOverlay" )
    @CacheLookup private List<WebElement> overlaysLocator ;

    @FindBy(how = How.CSS, using = "td[class='product-name']")
    @CacheLookup private WebElement productNameLocator;



    public CheckOutPage(WebDriver driver) {
        super(driver);
        overlays = new Overlays(driver);
    }

    public CheckOutPage load(){
        super.loadUrl("/checkout/");
        return this;
    }


    //  enter the user first name
    private CheckOutPage enterFirstName(String firstName) {
        WebElement element = super.getByVisibility(firstNameBox);
        element.clear();
        element.sendKeys(firstName);
        return this;
    }


    //    enter the user last name
    private CheckOutPage enterLastName(String lastName) {
        WebElement element = super.getByVisibility(lastNameBox);
        element.clear();
        element.sendKeys(lastName);
        return this;
    }



    private CheckOutPage setCountry(String country){
//        WebElement element = super.getByVisibility(billingCountryDropdown);
//        Select select = new Select(element);
//        select.selectByVisibleText(country);
        super.click(billingCountryDropdown);
        WebElement element = (WebElement) isClickable(getLocator(country));
        JavaScriptUtils.scrollView(driver,element);
        element.click();
        return this;
    }


    //    enter the billing address of user
    private CheckOutPage enterBillingAddress(String address) {
        WebElement element = super.getByVisibility(billingAddressBox);
        element.clear();
        element.sendKeys(address);
        return this;
    }



    //    enter the billing city of user
    private CheckOutPage enterBillingCity(String cityName) {
        WebElement element = super.getByVisibility(billingCityBox);
        element.clear();
        element.sendKeys(cityName);
        return this;
    }


    private CheckOutPage setState(String state){

        super.click(billingStateDropdown);
        WebElement element = (WebElement) isClickable(getLocator(state));
        JavaScriptUtils.scrollView(driver,element);
        element.click();
        return this;
    }


    //    enter the billing zip of the user
    private CheckOutPage enterBillingZip(String zipCode) {
        WebElement element = super.getByVisibility(billingZipBox);
        element.clear();
        element.sendKeys(zipCode);
        return this;
    }



    //    enter the user email for billing
    private CheckOutPage enterBillingEmail(String emailAddress) {
        WebElement element = super.getByVisibility(billingEmailBox);
        if (element.getAttribute("value").length() == 0){
            element.sendKeys(emailAddress);
        }

        return this;
    }



    //    this method helps to click on place order button to process further
    private void clickPlaceOrderButton() {
        overlays.waitForOverlayToInvisible(overlaysLocator);
        super.click(placeOrderButton);
    }


    //  This is the method which compresses all the key methods
//  for this page to place order
    public void placeOrder(BillingAddress billingAddress){
        enterFirstName(billingAddress.getFirstName()).
                enterLastName(billingAddress.getLastName()).
                setCountry(billingAddress.getCountry()).
                enterBillingAddress(billingAddress.getBillingAddress()).
                enterBillingCity(billingAddress.getBillingCity()).
                setState(billingAddress.getState()).
                enterBillingZip(billingAddress.getBillingZip()).
                enterBillingEmail(billingAddress.getEmailAddress()).
                clickPlaceOrderButton();

    }



//  this method helps to show the fields
//  what we need during the login
    public CheckOutPage clickLoginLink(){
        super.click(loginLink);
        return this;
    }

    private CheckOutPage enterEmail(String email){
        WebElement element = super.getByVisibility(userEmailField);
        element.sendKeys(email);
        return this;
    }

    private CheckOutPage enterPassword(String password){
        WebElement element = super.getByVisibility(userPasswordField);
        element.sendKeys(password);
        return this;
    }
    private CheckOutPage enterLoginDetails(String email, String password){
        enterEmail(email);
        enterPassword(password);
        return this;
    }

    private CheckOutPage clickLoginButton(){
        super.click(loginButton);
        return this;
    }


//  validates whether the confirmation message
//  is okay or not after proper wait using overlay
//  to disappear with proper waiting time
    public void confirmation(String text){
        overlays.waitForOverlayToInvisible(overlaysLocator);
        super.validateText(confirmationMessage, text);
    }

    public void validateSubText(String productName){
        super.validateSubText(productNameLocator,productName);
    }

    public CheckOutPage logIn(String email, String password){
        clickLoginLink()
                .enterLoginDetails(email,password)
                .clickLoginButton();
        return this;
    }
}