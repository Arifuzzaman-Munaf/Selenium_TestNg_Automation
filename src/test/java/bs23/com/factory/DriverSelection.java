package bs23.com.factory;

import bs23.com.constant.BrowserType;

import java.util.Locale;

public class DriverSelection {
    public static DriverManager selectDriver(String browserName){
        switch (BrowserType.valueOf(browserName.toUpperCase(Locale.ROOT))){
            case CHROME:
                return new ChromeDriverManager();

            case EDGE:
                return new EdgeDriverManager();

            case FIREFOX:
                return new FirefoxDriverManager();

            case OPERA:
                return new OperaDriverManager();

            default:
                throw new IllegalCallerException("Invalid Browser Name: "+ browserName);
        }

    }
}

