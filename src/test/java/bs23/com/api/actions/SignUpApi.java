package bs23.com.api.actions;

import bs23.com.objects.User;
import bs23.com.utilities.ConfigLoader;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.jsoup.Jsoup;


import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class SignUpApi {
    private Cookies cookies;

    public Cookies getCookies() {
        return cookies;
    }

    public String fetchNonceKey(){
        Response response = getAccount() ;
        return
                //

                Jsoup.parse(
                        response.body().prettyPrint()
                ).
                selectFirst(
                        "#woocommerce-register-nonce"
                ).
                attr(
                        "value"
                );
    }

    public Response getAccount(){
        cookies = new Cookies();
        Response response =
                given().
                        baseUri(ConfigLoader.getInstance().getBaseUrl()).
                        cookies(getCookies()).
                        log().all().
                when().
                        get("/account").
                then().
                        log().all().
                        extract().
                        response();


        if (response.getStatusCode() != 200){
            throw new
                    RuntimeException(
                            "Failed to fetch the account, Status Code : "+
                             response.getStatusCode()
                    );
        }

        return response;

    }


    public Response register(User user){
        Cookies cookies = new Cookies();
        Header header = new Header(
                                "content-type",
                                "application/x-www-form-urlencoded"
                        );
        Headers headers = new Headers(header);

        HashMap<String,String > formParam= new HashMap<>();
        formParam.put("username", user.getUsername());
        formParam.put("email", user.getEmail());
        formParam.put("password", user.getPassword());
        formParam.put("woocommerce-register-nonce", fetchNonceKey());
        formParam.put("register", "register");

        Response response =
                given().
                        baseUri(ConfigLoader.getInstance().getBaseUrl()).
                        headers(headers).
                        formParams(formParam).
                        cookies(getCookies()).
                        log().all().
                when().
                        post("/account").
                then().
                        log().all().
                        extract().
                        response();


        if (response.getStatusCode() != 302){
            throw new
                    RuntimeException(
                    "Failed to register the account, Status Code : "+
                            response.getStatusCode()
            );
        }

        this.cookies = response.getDetailedCookies();
        return response;

    }

}
