package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import static java.lang.Thread.sleep;
import static utils.WebDriverUtils.*;

public class BaseSteps {
    private static final String baseUrl = "https://ndelius.test.probation.service.justice.gov.uk";

    @Before
    public void launchBrowser() throws InterruptedException {
        setDriver();
        navigate(baseUrl);
        sleep(2000);
    }


    @After
    public void tearDown(){
        quit();
    }


}
