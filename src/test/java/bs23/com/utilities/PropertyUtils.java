package bs23.com.utilities;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

public class PropertyUtils {
    public static @NotNull Properties loader(String filePath){
        Properties properties = new Properties();
        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(filePath));
            try {
                properties.load(reader);
                reader.close();
            }
            catch (Exception e){
                e.printStackTrace();
                throw
                        new RuntimeException("Failed to load properties - " + filePath);
            }
        }
        catch (Exception e){
            throw
                    new RuntimeException("File not found - " + filePath);
        }
        return properties;
    }
}
