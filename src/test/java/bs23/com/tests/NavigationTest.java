package bs23.com.tests;

import bs23.com.base.BaseTest;
import bs23.com.pages.HomePage;
import bs23.com.pages.StorePage;
import org.testng.annotations.Test;

public class NavigationTest extends BaseTest {

    @Test(description = "Navigates to store page")
    public void navigateToStorePage(){
//    creating the HomePage object to interact with homePage
//    this line will navigate to webpage
        HomePage homePage = new HomePage(getDriver()).load();


//     this line click the store menu link
//     as well as returns the object of storePage
//      create fluent interface
//      Building strategy
        StorePage storePage = homePage.
                              getHeaderSection().
                              clickStoreMenuLink();


//      validating the page title
        storePage.validateTitle("Store");
    }
}
