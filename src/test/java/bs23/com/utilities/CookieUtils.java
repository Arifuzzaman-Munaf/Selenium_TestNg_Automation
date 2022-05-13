package bs23.com.utilities;

import io.restassured.http.Cookies;
import org.openqa.selenium.Cookie;

import java.util.ArrayList;
import java.util.List;

public class CookieUtils {

    public List<Cookie> convertedCookies(Cookies cookies){
        List<io.restassured.http.Cookie> restCookies = cookies.asList();
        List<Cookie> seleniumCookies = new ArrayList<>();
        for (io.restassured.http.Cookie cookie : restCookies){
            seleniumCookies.add(
                    new Cookie(
                            cookie.getName(),
                            cookie.getValue(),
                            cookie.getDomain(),
                            cookie.getPath(),
                            cookie.getExpiryDate(),
                            cookie.isSecured(),
                            cookie.isHttpOnly(),
                            cookie.getSameSite()
                    )
            );
        }
        return seleniumCookies;

    }
}
