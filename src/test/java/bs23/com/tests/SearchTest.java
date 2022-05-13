package bs23.com.tests;

import bs23.com.base.BaseTest;
import bs23.com.pages.StorePage;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    @Test(description = "Search item from Store Page")
    public void searchInStorePage(){
        StorePage storePage = new StorePage(getDriver()).load();

//      execute the search operation by taking the keyword
        storePage.search("Blue");

//      validating the page title
        storePage.validateTitle("Search results: “Blue”" );
    }

}
