package bs23.com.utilities;

import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader(){
        properties = PropertyUtils.loader("src/test/resources/config.properties");
    }

    public static ConfigLoader getInstance(){
        return  (configLoader == null)?
                new ConfigLoader():configLoader;

    }

    public String getBaseUrl(){
        String prop = properties.getProperty("baseUrl");
        if(prop != null) return prop;
        else throw
                new RuntimeException(
                        "baseUrl is not specified in config.properties file"
                );
    }

    public String getEmail(){
        String prop = properties.getProperty("email");
        if(prop != null) return prop;
        else throw
                new RuntimeException(
                        "email is not specified in config.properties file"
                );
    }


    public String getPassword(){
        String prop = properties.getProperty("password");
        if(prop != null) return prop;
        else throw
                new RuntimeException(
                        "password is not specified in config.properties file"
                );
    }

}
