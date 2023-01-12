package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import static utils.WebDriverUtils.*;


public class BaseSteps {
    private static final String baseUrl = System.getenv("URL");

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
