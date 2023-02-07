package frameworkTests;

import org.openqa.selenium.By;
import org.testng.annotations.*;
import utils.webDriver.interactions.SelectBoxUtils;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static utils.webDriver.Builder.getWebDriver;
import static utils.webDriver.Builder.initialiseWebDriver;


public class WebDriverInteractionTests {

    private static EmbeddedJetty embeddedJetty;

    @BeforeClass
    public static void setup() throws Exception {
        embeddedJetty = new EmbeddedJetty();
        embeddedJetty.start();
    }

    @AfterClass
    public static void teardown() throws Exception {
        embeddedJetty.stop();
    }

    @BeforeMethod
    public void setupWebapp() throws IOException {
        String baseUrl = "http://localhost:" + embeddedJetty.getPort() + "/index.html";
        initialiseWebDriver();
        getWebDriver().get(baseUrl);
    }

    @AfterMethod
    public void teardownWebDriver() {
        getWebDriver().quit();
    }

    @Test
    public void testSelectBoxUtilsItemByIndex()
    {
        SelectBoxUtils.selectItemByIndex(getWebDriver().findElement(By.id("select-box")), 3);
        assertThat(getWebDriver().findElement(By.id("select-box-option3")).isSelected(), is(true));
        assertThat(getWebDriver().findElement(By.id("select-box-option2")).isSelected(), is(false));
        assertThat(getWebDriver().findElement(By.id("select-box-option1")).isSelected(), is(false));
    }
    @Test
    public void testSelectBoxUtilsTolerantItemByIndex() throws Throwable {
        SelectBoxUtils.tolerantItemByIndex(getWebDriver().findElement(By.id("select-box")), 3);
        assertThat(getWebDriver().findElement(By.id("select-box-option3")).isSelected(), is(true));
        assertThat(getWebDriver().findElement(By.id("select-box-option2")).isSelected(), is(false));
        assertThat(getWebDriver().findElement(By.id("select-box-option1")).isSelected(), is(false));
    }

    @Test
    public void testSelectBoxUtilsItemByIndexWithAwaitility() {
        SelectBoxUtils.selectItemByIndexWithRetry(getWebDriver().findElement(By.id("select-box")), 3);
        assertThat(getWebDriver().findElement(By.id("select-box-option3")).isSelected(), is(true));
        assertThat(getWebDriver().findElement(By.id("select-box-option2")).isSelected(), is(false));
        assertThat(getWebDriver().findElement(By.id("select-box-option1")).isSelected(), is(false));
    }
}
