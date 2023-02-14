package stepDefinitions;

import com.google.common.io.BaseEncoding;
import data.SessionData;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.TestConfigManager;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static utils.webDriver.Builder.getWebDriver;
import static utils.webDriver.Builder.initialiseWebDriver;


public class BaseSteps {
    private String threadId;
    private String testId;
    private static final Logger logger = LoggerFactory.getLogger(BaseSteps.class);
    private static final ThreadLocal<SessionData> sessionData = new ThreadLocal<>();

    public static SessionData getSessionData() {
        return sessionData.get();
    }

    public static SessionData.OffenderData getOffenderSessionData() {
        return getSessionData().getOffenderData();
    }

    public static SessionData.EventData getEventSessionData() {
        return getSessionData().getEventData();
    }

    public static SessionData.ContactData getContactSessionData() {
        return getSessionData().getContactData();
    }

    @Before
    public void debugThreads() {
        this.threadId = "Thread ID: " + Thread.currentThread().getId();
        this.testId = generateTestId();
        logger.debug(threadId + " testId: " + testId);
        sessionData.set(new SessionData());
        getSessionData().setOffenderData(new SessionData.OffenderData());
        getSessionData().setEventData(new SessionData.EventData());
        getSessionData().setContactData(new SessionData.ContactData());
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
        logger.debug(threadId + " testId: " + testId);

        getWebDriver().manage().deleteAllCookies();
        getWebDriver().close();
    }

    @AfterAll
    public static void shutdown() {
        if (null != getWebDriver() && !getWebDriver().toString().contains("Safari")) {
            logger.debug("What is WebDriver? : " + getWebDriver().toString());
            getWebDriver().quit();
        }
    }

    private String generateTestId() {
        UUID uuid = UUID.randomUUID();
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return StringUtils.replace(BaseEncoding.base64Url().encode(bb.array()), "=", "" ).toUpperCase();
    }

}