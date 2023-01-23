package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFileReader {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesFileReader.class);

    static String result = "";
    private static InputStream inputStream;

    public static String getPropertyValueFromFile(String key) throws IOException {
        String env = System.getenv("ENV").toLowerCase();
        try {
            Properties prop = new Properties();
            String propFileName = env+".properties";
            inputStream = PropertiesFileReader.class.getClassLoader().getResourceAsStream(propFileName);
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            result = prop.getProperty(key);
        } catch (Exception e) {
            logger.error("Exception: " + e);
        } finally {
            assert inputStream != null;
            inputStream.close();
        }
        return result;
    }

}
