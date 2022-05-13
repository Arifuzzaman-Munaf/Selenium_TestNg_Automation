package bs23.com.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class JacksonUtils {

//  Generic method for fetching json data
    public static  <T> T deserializeJson(
            String fileName, Class<T> T
    ) throws IOException {

//      fetching json data of billing information
//      from json file as input stream
        InputStream inputStream = JacksonUtils.class.
                                    getClassLoader().
                                    getResourceAsStream(fileName);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(inputStream, T);
    }
}
