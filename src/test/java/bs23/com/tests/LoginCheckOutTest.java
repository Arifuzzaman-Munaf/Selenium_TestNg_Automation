package bs23.com.tests;

import bs23.com.api.actions.CartApi;
import bs23.com.api.actions.SignUpApi;
import bs23.com.base.BaseTest;
import bs23.com.objects.BillingAddress;
import bs23.com.objects.Product;
import bs23.com.objects.User;
import bs23.com.pages.CheckOutPage;
import bs23.com.utilities.FakerUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginCheckOutTest extends BaseTest {

    @Test(description = "Checkout as Logged in User")
    public void loginCheckOut() throws IOException, InterruptedException {
        FakerUtils fakerUtils = new FakerUtils();
        User user = new User(
                fakerUtils.getUserName(),
                fakerUtils.getEmail(),
                fakerUtils.getPassword()
        );

        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);

        CartApi cartApi = new CartApi(signUpApi.getCookies());
        String productFileName = "products.json";

//      fetching product data according to product id
//      using dynamic locators
        Product item = new Product(1215, productFileName);
        cartApi.addToCart(item.getId(), 1);

        CheckOutPage checkOutPage = new CheckOutPage(getDriver()).load();
        Thread.sleep(500);
        injectCookies(signUpApi.getCookies());

        Thread.sleep(500);
        String addressFileName = "MyBillingAddress.json";
        BillingAddress billingAddress = new BillingAddress(addressFileName);
        checkOutPage.load();
        checkOutPage.placeOrder(billingAddress);

        /*
        validates whether the products are successfully
        purchased or not using confirmation text
         */
        checkOutPage.confirmation("Thank you. Your order has been received.");
    }
}

