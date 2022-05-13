package bs23.com.api;

import bs23.com.api.actions.CartApi;
import bs23.com.api.actions.SignUpApi;
import bs23.com.objects.User;
import bs23.com.utilities.FakerUtils;


public class dummy {
    public static void main(String[] args) {
        FakerUtils fakerUtils = new FakerUtils();
        User user = new User(
                        fakerUtils.getUserName(),
                        fakerUtils.getEmail(),
                        fakerUtils.getPassword()
                    );

        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);
        System.out.println("Register Coockies :" + signUpApi.getCookies());

        CartApi cartApi = new CartApi(signUpApi.getCookies());
        cartApi.addToCart(1215, 1);
        System.out.println("Cart Coockies :" + cartApi.getCookies());

    }

}
