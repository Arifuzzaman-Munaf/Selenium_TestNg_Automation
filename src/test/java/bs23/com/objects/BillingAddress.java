package bs23.com.objects;

import bs23.com.utilities.JacksonUtils;

import java.io.IOException;

public class BillingAddress {


//  All the fields we need to provide for billing
    private String firstName;
    private String lastName;

    private String country;
    private String state;
    private String billingAddress;
    private String billingCity;
    private String billingZip;
    private String emailAddress;

    public BillingAddress() {
    }

    public BillingAddress(String fileName) throws IOException {

        BillingAddress billingAddress = JacksonUtils.deserializeJson(
                fileName, BillingAddress.class);

        this.firstName      = billingAddress.getFirstName();
        this.lastName       = billingAddress.getLastName();
        this.country        = billingAddress.getCountry();
        this.billingAddress = billingAddress.getBillingAddress();
        this.billingCity    = billingAddress.getBillingCity();
        this.state          = billingAddress.getState();
        this.billingZip     = billingAddress.getBillingZip();
        this.emailAddress   = billingAddress.getEmailAddress();
    }



    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }
    //  Getter methods to fetch data regarding billing
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public String getBillingZip() {
        return billingZip;
    }

    public String getEmailAddress() {
        return emailAddress;
    }


}
