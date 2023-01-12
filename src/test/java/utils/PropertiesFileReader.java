package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFileReader {

    static String result = "";
    private static InputStream inputStream;

    public static String getHostName() throws IOException {
        String url = System.getenv("URL");
        return getPropValue(url);
    }

    public static String getPropValue(String key) throws IOException {
        //String env = System.getenv("ENV");
        String env = "test";
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
            System.out.println("Exception: " + e);
        } finally {
            assert inputStream != null;
            inputStream.close();
        }
        return result;
    }

}
