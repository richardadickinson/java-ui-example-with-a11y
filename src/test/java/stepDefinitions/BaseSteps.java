package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.io.IOException;

import static utils.PropertiesFileReader.getPropertyValueFromFile;
import static utils.WebDriverUtils.*;


public class BaseSteps
{
    @Before
    public void debugThreads()
    {
        String threadId = "Thread ID" + Thread.currentThread().threadId();
        System.out.println(threadId);
    }

    @Before("not @api")
    public void launchBrowser() throws IOException
    {
        String baseUrl = getPropertyValueFromFile("baseUrl");
        setDriver();
        navigate(baseUrl);
    }

    @After("not @api")
    public void tearDown(){
        quit();
    }


}
