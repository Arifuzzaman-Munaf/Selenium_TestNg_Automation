package bs23.com.tests;

import bs23.com.api.actions.CartApi;
import bs23.com.api.actions.SignUpApi;
import bs23.com.base.BaseTest;
import bs23.com.objects.Product;
import bs23.com.objects.User;
import bs23.com.pages.CheckOutPage;
import bs23.com.utilities.FakerUtils;
import org.testng.annotations.Test;

import java.io.IOException;


public class LoginTest extends BaseTest {


    @Test(description = "Login during checkout")
    public void loginDuringCheckout() throws IOException, InterruptedException {

//      creates fake data using Faker library
        FakerUtils fakerUtils = new FakerUtils();
        User user = new User(
                fakerUtils.getUserName(),
                fakerUtils.getEmail(),
                fakerUtils.getPassword()
        );

        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);

        CartApi cartApi = new CartApi();
        String productFileName = "products.json";

//      fetching product data according to product id
//      using dynamic locators
        Product item = new Product(1215, productFileName);
        cartApi.addToCart(item.getId(), 1);

        CheckOutPage checkOutPage = new CheckOutPage(getDriver()).load();
        Thread.sleep(500);
        injectCookies(cartApi.getCookies());
        checkOutPage.load();
        Thread.sleep(500);
        checkOutPage.
                logIn(
                        user.getEmail(),user.getPassword()
                );

        checkOutPage.validateSubText(item.getName());

    }
}
