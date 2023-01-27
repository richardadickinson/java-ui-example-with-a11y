package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static utils.PropertiesFileReader.getPropertyValueFromFile;
import static utils.WebDriverUtils.*;


public class BaseSteps {
//    @Before
//    public void debugThreads() {
//        String threadId = "Thread ID" + Thread.currentThread().getId();
//        System.out.println(threadId);
//    }

    @Before("not @api")
    public void launchBrowser() throws IOException {
        String baseUrl = getPropertyValueFromFile("base_url");
        setDriver();
        navigate(baseUrl);
    }

    @After("not @api")
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            SimpleDateFormat timestamp = new SimpleDateFormat(("yyyy.MM.dd.HH.mm.ss"));
            byte[] screenshot = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName() + "_" + timestamp.format(new Date()));
        }
        quit();
    }

}