package stepDefinitions;

import com.google.common.io.BaseEncoding;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.TestConfigManager;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static utils.WebDriverUtils.getWebDriver;
import static utils.WebDriverUtils.initialiseWebDriver;


public class BaseSteps {

    private String threadId;
    private String testId;

    @Before
    public void debugThreads() {
        this.threadId = "Thread ID: " + Thread.currentThread().getId();
        this.testId = generateTestId();
        System.out.println(threadId + " testId: " + testId);
    }

    @Before("not @api")
    public void launchWebDriver() throws IOException {
        initialiseWebDriver();
        getWebDriver().get(TestConfigManager.get().getBaseUrl());
    }

    @After("not @api")
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            SimpleDateFormat timestamp = new SimpleDateFormat(("yyyy.MM.dd.HH.mm.ss"));
            byte[] screenshot = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName() + "_" + timestamp.format(new Date()));
        }
        this.threadId = "Thread ID: " + Thread.currentThread().getId();
        System.out.println(threadId + " testId: " + testId);

        getWebDriver().manage().deleteAllCookies();
        getWebDriver().close();
    }

    @AfterAll
    public static void shutdown() {
        if (null != getWebDriver()) { getWebDriver().quit(); }
    }

    private String generateTestId() {
        UUID uuid = UUID.randomUUID();
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        String testId = StringUtils.replace(BaseEncoding.base64Url().encode(bb.array()), "=", "" ).toUpperCase();
        return testId;
    }

}