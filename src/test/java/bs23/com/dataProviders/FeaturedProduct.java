package bs23.com.dataProviders;

import bs23.com.objects.Product;
import bs23.com.utilities.JacksonUtils;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class FeaturedProduct {
    @DataProvider(name = "getFeaturedProduct", parallel = true)
    public Product[] getFeaturedProduct() throws IOException {
        return JacksonUtils.deserializeJson(
                "products.json", Product[].class
              );
    }
}
