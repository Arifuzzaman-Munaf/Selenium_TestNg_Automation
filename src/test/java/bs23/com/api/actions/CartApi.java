package bs23.com.api.actions;

import bs23.com.utilities.ConfigLoader;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class CartApi {

    private Cookies cookies;

    public CartApi(){

    }

    public CartApi(Cookies cookies){
        this.cookies = cookies;
    }

    public Cookies getCookies(){
        return this.cookies;
    }

    public Response addToCart(int prodID, int amount){
        Header header = new Header(
                "content-type",
                "application/x-www-form-urlencoded"
        );
        Headers headers = new Headers(header);

        HashMap<String,Object > formParam= new HashMap<>();
        formParam.put("product_sku", "");
        formParam.put("product_id", prodID);
        formParam.put("quantity", amount);

        if (this.cookies == null){
            this.cookies = new Cookies();
        }

        Response response =
                given().
                        baseUri(ConfigLoader.getInstance().getBaseUrl()).
                        headers(headers).
                        formParams(formParam).
                        cookies(cookies).
                        log().all().
                when().
                        post("/?wc-ajax=add_to_cart").
                then().
                        log().all().
                        extract().
                        response();


        if (response.getStatusCode() != 200){
            throw new
                    RuntimeException(
                    "Failed to add the item of ID - ,"+ prodID+
                    "Status Code : "+ response.getStatusCode()
            );
        }

        this.cookies = response.getDetailedCookies();
        return response;

    }
}
