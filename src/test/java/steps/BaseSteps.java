package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.io.IOException;

import static utils.PropertiesFileReader.getHostName;
import static utils.WebDriverUtils.*;

public class BaseSteps {
    private static final String baseUrl;

    static {
        try {
            baseUrl = getHostName();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Before
    public void launchBrowser() {
        setDriver();
        navigate(baseUrl);
    }

    @After
    public void tearDown(){
        quit();
    }


}
