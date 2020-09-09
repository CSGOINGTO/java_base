package base_knowledge.rpc.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtils {

    private static final Properties PROPERTIES = new Properties();

    public static String getProperties(String key) {

        try {
            PROPERTIES.load(new BufferedInputStream(new FileInputStream("src/main/resources/config.properties")));
            return PROPERTIES.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
