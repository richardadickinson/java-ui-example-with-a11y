package frameworkTests.unitTests;

import config.BrowserType;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.TestConfigManager;
import utils.webDriver.config.WebDriverConfig;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestConfigManagerTests {

    @BeforeTest
    public void setUp() {
        System.setProperty("config", "fixtures/test-config.json");
    }

    @Test
    public void testReturnedTestConfigIsCorrectType() {
        assertThat(TestConfigManager.get(), instanceOf(WebDriverConfig.class));
    }

    @Test
    public void testCanAccessTestConfigViaSingleton() {
        assertThat(TestConfigManager.get().getBrowserType(), is(BrowserType.CHROME));
        assertThat(TestConfigManager.get().isHeadless().toString(), is("true"));
        assertThat(TestConfigManager.get().getTestConfig("sample"), is("sample text"));
        assertThat(TestConfigManager.get().getDefaultWindowSize().toString(), is("(1400, 800)"));
        assertThat(TestConfigManager.get().getBrowserPreferences(BrowserType.CHROME).toString(), containsString("safebrowsing.enabled"));
    }

}
