package bs23.com.tests;

import bs23.com.base.BaseTest;
import bs23.com.dataProviders.FeaturedProduct;
import bs23.com.objects.Product;
import bs23.com.pages.CartPage;
import bs23.com.pages.HomePage;
import bs23.com.pages.StorePage;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddToCartTest extends BaseTest {

    @Test(description = "Add a featured product from Store Page")
    public void addToCartFromStorePage() throws IOException {
        StorePage storePage = new StorePage(getDriver()).load();
//      This variable contains the json file regarding product details
        String productFileName = "products.json";

//      fetching product data according to product id
//      using dynamic locators
        Product item = new Product(1215, productFileName);


        storePage.
                getProductFunctions().
                clickAddToCartButton(item.getName());


        storePage.
                getProductFunctions().
                //this line will navigate to cart page
                        clickViewCartLink().
                /*validating the product name is okay or not*/
                        validateProduct(item.getName());

    }

    @Test(
            description = "Add a featured product from Home Page",
            dataProvider = "getFeaturedProduct",
            dataProviderClass = FeaturedProduct.class
    )
    public void addToCartFromHomePage(Product product) {
        HomePage homePage = new HomePage(getDriver());
        CartPage cartPage = homePage.load().
                            getProductFunctions().
                            clickAddToCartButton(product.getName()).
                            clickViewCartLink();

        cartPage.validateProduct(product.getName());
    }

}
