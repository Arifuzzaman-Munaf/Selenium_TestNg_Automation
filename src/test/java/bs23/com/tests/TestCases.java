package bs23.com.tests;


import bs23.com.base.BaseTest;
import bs23.com.objects.BillingAddress;
import bs23.com.objects.Product;
import bs23.com.pages.CartPage;
import bs23.com.pages.CheckOutPage;
import bs23.com.pages.HomePage;
import bs23.com.pages.StorePage;
import bs23.com.utilities.ConfigLoader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import java.io.IOException;





public class TestCases extends BaseTest {

    @Test
    public void testCase_001() throws IOException {


//      passing the Json file name to deserialize
//      data we need for billing and
//      returning a instance of BillingAddress class
//      after initializing the variables existing in BillingAddress class
        String addressFileName = "MyBillingAddress.json";
        BillingAddress billingAddress = new BillingAddress(addressFileName);




//      creating the HomePage object to interact with homePage
//      this line will navigate to webpage
        HomePage homePage = new HomePage(getDriver()).load();


//      this line click the store menu link
//      as well as returns the object of storePage
//      create fluent interface
//      Building strategy
        StorePage storePage = homePage.getHeaderSection().
                                clickStoreMenuLink();

//      execute the search operation by taking the keyword
        storePage.search("Blue");

//      validating the page title
        storePage.validateTitle("Search results: “Blue”" );


//      This variable contains the json file regarding product details
        String productFileName = "products.json";

//      fetching product data according to product id
//      using dynamic locators
        Product item = new Product(1215, productFileName);

//      click to add cart button for specific type of product

        storePage.getProductFunctions().clickAddToCartButton(item.getName());



//      view cart items what we have added
//      this line will navigate to cart page
//      and returns an object for cartPage
        CartPage cartPage = storePage.getProductFunctions().clickViewCartLink();


//      validating the product name is okay or not
        cartPage.validateProduct(item.getName());


//      this line navigates to Billing Page
//      click on proceed to check out
        CheckOutPage checkOutPage = cartPage.clickCheckOutButton();


//      this method helps the user to log in the system
//      using singleton design pattern
//      where the user data has been fetched from config.properties file
        checkOutPage.logIn(
                ConfigLoader.getInstance().getEmail(),
                ConfigLoader.getInstance().getPassword()
        );

//       these methods provide all the necessary information
//       regarding billing user and process the steps
//       for place order successfully
        checkOutPage.placeOrder(billingAddress);



//      validates whether the products are successfully purchased or not
//      using confirmation text
        checkOutPage.confirmation("Thank you. Your order has been received.");
    }



    @Test
    public void testCase_002() throws IOException {


//      passing the Json file name to deserialize
//      data we need for billing and
//      returning a instance of BillingAddress class
//      after initializing the variables existing in BillingAddress class
        String addressFileName = "MyBillingAddress.json";
        BillingAddress billingAddress = new BillingAddress(addressFileName);
        WebDriver driver = getDriver();



//      creating the HomePage object to interact with homePage
//      this line will navigate to webpage
        HomePage homePage = new HomePage(driver).load();


//      this line click the store menu link
//      as well as returns the object of storePage
//      create fluent interface
//      Building strategy
        StorePage storePage = homePage.getHeaderSection().clickStoreMenuLink();

//      execute the search operation by taking the keyword
        storePage.search("Blue");

//      validating the page title
        storePage.validateTitle("Search results: “Blue”" );


//      This variable contains the json file regarding product details
        String productFileName = "products.json";

//      fetching product data according to product id
//      using dynamic locators
        Product item = new Product(1215, productFileName);

//      click to add cart button for specific type of product

        storePage.getProductFunctions().clickAddToCartButton(item.getName());



//      view cart items what we have added
//      this line will navigate to cart page
//      and returns an object for cartPage
        CartPage cartPage = storePage.getProductFunctions().clickViewCartLink();


//      validating the product name is okay or not
        cartPage.validateProduct(item.getName());


//      this line navigates to Billing Page
//      click on proceed to check out
        CheckOutPage checkOutPage = cartPage.clickCheckOutButton();


//       these methods provide all the necessary information
//       regarding billing user and process the steps
//       for place order successfully
        checkOutPage.placeOrder(billingAddress);



//      validates whether the products are successfully purchased or not
//      using confirmation text
        checkOutPage.confirmation("Thank you. Your order has been received.");
    }

}
