package frameworkTests.unitTests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.ConfigLoader;
import utils.webDriver.config.WebDriverConfig;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ConfigFileLoaderTests {

    private ConfigLoader configLoader;

    @BeforeTest
    public void setUp() {
        this.configLoader = new ConfigLoader();
    }

    @Test
    public void testCanLoadConfigFileWithConfigPropertySetToDefaultAndNoPropertyArgsSet() {
        System.setProperty("config", "DEFAULT");
        utils.webDriver.config.WebDriverConfig webDriverConfig = configLoader
                .chooseTargetConfiguration()
                .build();
        assertThat(webDriverConfig.getBaseUrl(), is("https://my-test-app.test.com"));
    }

    @Test
    public void testCanLoadConfigFileWithConfigPropertySetToExternalFile() {
        System.setProperty("config", "fixtures/test-config.json");
        WebDriverConfig webDriverConfig = configLoader
                .chooseTargetConfiguration()
                .build();
        assertThat(webDriverConfig.isHeadless(), is(true));
    }
}
