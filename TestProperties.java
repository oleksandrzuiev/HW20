package homeWork20;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {
    private final Properties properties;

    public TestProperties() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("src/test/resources/homeWork20.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        if (properties == null) {
            return null;
        }
        return properties.getProperty(key);
    }
}
