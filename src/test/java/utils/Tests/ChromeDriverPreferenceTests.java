package utils.Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.configuredDrivers.ConfiguredChromeDriver;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ChromeDriverPreferenceTests {

    private static String baseUrl;
    private static EmbeddedJetty embeddedJetty;
    private WebDriver webDriver;

    @BeforeTest
    public static void webDriverSetup() throws Exception {
        embeddedJetty = new EmbeddedJetty();
        embeddedJetty.start();
        baseUrl = "http://localhost:" + embeddedJetty.getPort() + "/index.html";
    }

    @Test
    public void testChromeBrowserPreferencesAreAppliedAndFileDownloadWorks() throws IOException, InterruptedException {
        System.setProperty("config", "fixtures/sample-config-with-chrome-preferences.json");
        webDriver = new ConfiguredChromeDriver().getDriver();
        webDriver.get(baseUrl);
        String expectedFile = new File("target/run-generated-files/chrome/testDownloads").getCanonicalPath() + "/sampleFile.pdf";
        webDriver.findElement(By.xpath("//a[text()='clickHereToDownLoadAFile']")).click();
        Thread.sleep(3000); // need to wait for file download
        assertThat("Expected downloaded file to exist in run-generated-files/chrome/downloads", new File(expectedFile).exists(), is(true));
    }

    @AfterTest
    public void tearDown(){
        this.webDriver.quit();
    }
    @AfterClass
    public static void jettyTearDown() throws Exception {
        System.setProperty("config", "DEFAULT");
        embeddedJetty.stop();
    }
}
