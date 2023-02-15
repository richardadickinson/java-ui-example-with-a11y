package frameworkTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import utils.webDriver.configuredDrivers.ConfiguredChromeDriver;
import utils.webDriver.configuredDrivers.ConfiguredDriver;
import utils.webDriver.configuredDrivers.ConfiguredEdgeDriver;
import utils.webDriver.configuredDrivers.ConfiguredFirefoxDriver;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ConfiguredDriverPreferenceTests {

    Logger logger = LoggerFactory.getLogger(ConfiguredDriverPreferenceTests.class);
    private static String baseUrl;
    private static EmbeddedJetty embeddedJetty;
    private WebDriver webDriver;

    @DataProvider (name = "configured-drivers")
    public Object[][] dpMethod(){
        return new Object[][] {{new ConfiguredChromeDriver()}, {new ConfiguredFirefoxDriver()}, {new ConfiguredEdgeDriver()}};
    }

    @BeforeTest
    public void webDriverSetup() throws Exception {
        embeddedJetty = new EmbeddedJetty();
        embeddedJetty.start();
        baseUrl = "http://localhost:" + embeddedJetty.getPort() + "/index.html";
    }

    @Test (dataProvider = "configured-drivers")
    public void testBrowserPrefsAreAppliedAndFileDownloadWorks(ConfiguredDriver testDriver) throws IOException, InterruptedException {
        System.setProperty("config", "fixtures/sample-config-with-browser-preferences.json");
        webDriver = testDriver.getDriver();
        webDriver.get(baseUrl);
        String expectedFile = new File("target/run-generated-files/browser/testDownloads").getCanonicalPath() + "/sampleFile.pdf";
        webDriver.findElement(By.xpath("//a[text()='clickHereToDownLoadAFile']")).click();
        Thread.sleep(3000); // need to wait for file download
        assertThat("Expected downloaded file to exist in run-generated-files/browser/downloads", new File(expectedFile).exists(), is(true));
    }

    @AfterMethod
    public void tearDown(Method method) throws IOException {
        File targetFile = new File(new File("target/run-generated-files/browser/testDownloads").getCanonicalPath() + "/sampleFile.pdf");
        if (targetFile.exists()) {
            boolean deleted = targetFile.delete();
            if (!deleted) {
                logger.error("Target file was not deleted by " + method.getName());
            }
        } else {
            logger.error("Target file was not found for deletion in " + method.getName());
        }
        this.webDriver.quit();
    }
    @AfterClass
    public void jettyTearDown() throws Exception {
        System.setProperty("config", "DEFAULT");
        embeddedJetty.stop();
        this.webDriver.quit();
    }
}
