package bs23.com.objects;

import bs23.com.utilities.JacksonUtils;
import io.qameta.allure.Step;

import java.io.IOException;

public class Product {
    private int id;
    private String name;

    public Product() {
    }

    public Product(int id, String fileName) throws IOException {
        Product[] products = JacksonUtils.deserializeJson(fileName, Product[].class);
        for (Product item : products){
            if (item.id == id) {
                this.id = item.getId();
                this.name = item.getName();
                break;
            }
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Step
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
