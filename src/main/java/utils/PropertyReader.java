package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    public static String propertyReader(String key){
        String value;
        Properties prop = new Properties();
        try {
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/config.properties");
            prop.load(fis);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        value = prop.getProperty(key);

        return value;


    }
}
