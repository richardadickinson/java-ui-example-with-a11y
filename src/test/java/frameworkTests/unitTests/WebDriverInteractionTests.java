package frameworkTests.unitTests;

import org.awaitility.core.ConditionTimeoutException;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.webDriver.interactions.Click;
import utils.webDriver.interactions.FindBy;
import utils.webDriver.interactions.SelectBox;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertThrows;
import static utils.webDriver.Builder.getWebDriver;
import static utils.webDriver.Builder.initialiseWebDriver;
import static utils.webDriver.interactions.SelectBox.selectItemByIndexWithRetry;


public class WebDriverInteractionTests {

    private static EmbeddedJetty embeddedJetty;

    @BeforeClass
    public void setup() throws Exception {
        embeddedJetty = new EmbeddedJetty();
        embeddedJetty.start();
    }

    @AfterClass
    public void teardown() throws Exception {
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

    /**
     * Find By tests
     */
    @Test
    public void testFindByUntilFindsElement() {
        try {
            FindBy.findByUntil(getWebDriver(), By.id("ClickUtils-href"));
        } catch (Exception ex) {
            Assert.fail("Expected no exception but got: " + ex.getMessage());
        }
    }

    @Test
    public void testFindByUntilTimesOutWhenElementNotFound() {
        assertThrows(ConditionTimeoutException.class,
                () -> FindBy.findByUntil(getWebDriver(), By.id("cant-find-me"), 1));
    }

    @Test
    public void testTolerantFindByIdOrNameWithValidId() {
        FindBy.findByIdOrName(getWebDriver(), "ClickUtils-href").click();
        assertThat(getWebDriver().getCurrentUrl(), is("http://localhost:"+embeddedJetty.getPort()+"/hello-passed.html"));
    }

    @Test
    public void testFindByLinkWithValidLinkText() {
        FindBy.findByLinkText(getWebDriver(), "Button 2 (displayed)").click();
        assertThat(getWebDriver().getCurrentUrl(), is("http://localhost:"+embeddedJetty.getPort()+"/hello-passed.html"));
    }

    @Test
    public void testFindByLinkThrowsWhenElementNotFound() {
        assertThrows(NoSuchElementException.class,
                () -> FindBy.findByLinkText(getWebDriver(), "cant-find-me"));
    }

    /**
     * Select box tests
     */
    @Test
    public void testSelectBoxSelectItemByIndex() {
        SelectBox.selectItemByIndex(getWebDriver().findElement(By.id("select-box")), 3);
        assertThat(getWebDriver().findElement(By.id("select-box-option3")).isSelected(), is(true));
        assertThat(getWebDriver().findElement(By.id("select-box-option2")).isSelected(), is(false));
        assertThat(getWebDriver().findElement(By.id("select-box-option1")).isSelected(), is(false));
    }

    @Test
    public void testSelectBoxTolerantSelectItemByIndex() throws Throwable {
        SelectBox.tolerantItemByIndex(getWebDriver().findElement(By.id("select-box")), 3);
        assertThat(getWebDriver().findElement(By.id("select-box-option3")).isSelected(), is(true));
        assertThat(getWebDriver().findElement(By.id("select-box-option2")).isSelected(), is(false));
        assertThat(getWebDriver().findElement(By.id("select-box-option1")).isSelected(), is(false));
    }

    @Test
    public void testSelectBoxSelectItemByIndexWithAwaitility() {
        selectItemByIndexWithRetry(getWebDriver().findElement(By.id("select-box")), 3);
        assertThat(getWebDriver().findElement(By.id("select-box-option3")).isSelected(), is(true));
        assertThat(getWebDriver().findElement(By.id("select-box-option2")).isSelected(), is(false));
        assertThat(getWebDriver().findElement(By.id("select-box-option1")).isSelected(), is(false));
    }

    /**
     * async Click tests
     */
    @Test
    public void testRetryClickWhenElementStartsEnabled() {
        WebElement element = getWebDriver().findElement(By.id("clickutils-href"));
        Click.clickWithRetry(element);
        assertThat(getWebDriver().getCurrentUrl(), is("http://localhost:"+embeddedJetty.getPort()+"/hello-passed.html"));
    }

    @Test
    public void testClickUntilWhenElementStartsDisabled() {
        WebElement element = getWebDriver().findElement(By.id("clickutils-href-disabled"));
        Click.clickUntil(element, 30);
        assertThat(getWebDriver().getCurrentUrl(), is("http://localhost:"+embeddedJetty.getPort()+"/hello-passed.html"));
    }

    @Test
    public void testRetryClickThrowsExceptionWhenElementIsDisabled() {
        assertThrows(ElementNotInteractableException.class, () -> {
            WebElement element = getWebDriver().findElement(By.id("clickutils-href-always-disabled"));
            Click.clickWithRetry(element);
        });
    }

    @Test
    public void testClickUntilThrowsExceptionWhenTimeoutExpires() {
        assertThrows(ConditionTimeoutException.class, () -> {
            WebElement element = getWebDriver().findElement(By.id("clickutils-href-always-disabled"));
            Click.clickUntil(element);
        });
    }

    /**
     * tolerantClick tests
     */
    @Test
    public void testTolerantClickThrowsExceptionWhenElementStaysDisabled() {
        assertThrows(TimeoutException.class, () -> {
            WebElement element = getWebDriver().findElement(By.id("clickutils-href-always-disabled"));
            Click.tolerantClick(element, 3);
        });
    }

    @Test
    public void testTolerantClickWhenElementStartsDisabled() throws Throwable {
        WebElement element = getWebDriver().findElement(By.id("clickutils-href-disabled"));
        Click.tolerantClick(element, 30);
        assertThat(getWebDriver().getCurrentUrl(), is("http://localhost:"+embeddedJetty.getPort()+"/hello-passed.html"));
    }

    @Test
    public void testTolerantClickWhenElementStartsEnabled() throws Throwable {
        WebElement element = getWebDriver().findElement(By.id("clickutils-href"));
        Click.tolerantClick(element);
        assertThat(getWebDriver().getCurrentUrl(), is("http://localhost:"+embeddedJetty.getPort()+"/hello-passed.html"));
    }


}
