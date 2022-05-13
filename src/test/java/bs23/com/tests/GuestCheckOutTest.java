package bs23.com.tests;


import bs23.com.api.actions.CartApi;
import bs23.com.base.BaseTest;
import bs23.com.objects.BillingAddress;
import bs23.com.pages.CheckOutPage;
import org.testng.annotations.Test;

import java.io.IOException;

public class GuestCheckOutTest extends BaseTest {

    @Test(description = "Checkout as guest user")
    public void guestCheckOut() throws IOException {
        CheckOutPage checkOutPage = new CheckOutPage(getDriver()).load();
        CartApi cartApi = new CartApi();
        cartApi.addToCart(1215, 1);
        injectCookies(cartApi.getCookies());

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
