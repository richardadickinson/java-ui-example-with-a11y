package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import static utils.WebDriverUtils.*;


public class BaseSteps {
    private static final String baseUrl = System.getenv("URL");


    @Before("not @api")
    public void launchBrowser() {
        setDriver();
        navigate(baseUrl);
    }

    @After("not @api")
    public void tearDown(){
        quit();
    }


}
