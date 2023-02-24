package stepDefinitions;

import com.google.common.io.BaseEncoding;
import data.Person;
import data.SessionData;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.TestConfigManager;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static utils.dbUtils.DeleteScript.deleteOffendersByPerson;
import static utils.webDriver.Builder.getWebDriver;
import static utils.webDriver.Builder.initialiseWebDriver;


public class BaseSteps {
    private long threadId;
    private String testId;
    private static final Logger logger = LoggerFactory.getLogger(BaseSteps.class);
    private static final ThreadLocal<SessionData> sessionData = new ThreadLocal<>();

    public static SessionData getSessionData() {
        return sessionData.get();
    }

    @Before
    public void debugThreads() {
        this.threadId = Thread.currentThread().getId();
        this.testId = generateTestId();
        logger.debug("Thread ID: " + threadId + " testId: " + testId);
        sessionData.set(new SessionData());
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
        assertEquals(Thread.currentThread().getId(), this.threadId);
        logger.debug("Thread ID: " + Thread.currentThread().getId() + " testId: " + testId);

        signOutNDelius();
        getWebDriver().manage().deleteAllCookies();
        getWebDriver().close();

        ArrayList<Person> persons = getSessionData().getPersons();
        if (persons!=null){
            deleteOffendersByPerson(persons);
        }
    }

    @AfterAll
    public static void shutdown() {
        if (null != getWebDriver() && !getWebDriver().toString().contains("Safari")) {
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

    private void signOutNDelius(){
        getWebDriver().findElement(By.linkText("Sign Out")).click();
        Alert alert = new WebDriverWait(getWebDriver(), Duration.ofMillis(2000)).until(ExpectedConditions.alertIsPresent());
        while (null != alert) {
            getWebDriver().switchTo().alert().accept();
            logger.debug("Sign Out alert accepted");
            try {
                alert = new WebDriverWait(getWebDriver(), Duration.ofMillis(1000)).until(ExpectedConditions.alertIsPresent());
            } catch (TimeoutException tex) {
                alert = null;
            }
        }

        assertTrue(getWebDriver().getCurrentUrl().contains("close.jsp"));
    }

}