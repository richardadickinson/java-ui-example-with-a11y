package utils.Tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import config.BrowserType;
import config.WebDriverConfig;
import org.testng.annotations.Test;
import utils.JsonUtils;

import java.io.IOException;
import java.net.MalformedURLException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertThrows;

public class WebDriverConfigTests {

    @Test
    public void testCanCreateInstanceFromJsonFileAndTestGetters() throws IOException {
        WebDriverConfig webDriverConfig = JsonUtils.fromFile(ClassLoader.getSystemResourceAsStream("fixtures/test-config.json"),
                WebDriverConfig.class);
        assertThat(webDriverConfig.getBaseUrl(), is("https://google.com"));
    }

    @Test
    public void testGetBrowserPreferencesReturnsAnEmptyNodeIfNoBrowserOptionsArePresentInConfig() throws JsonProcessingException {
        String configJson = "{}";
        WebDriverConfig webDriverConfig = JsonUtils.fromString(configJson, WebDriverConfig.class);

        ObjectNode actualPrefs = webDriverConfig.getBrowserPreferences(BrowserType.CHROME);
        assertThat(actualPrefs, is(JsonNodeFactory.instance.objectNode()));
    }

    @Test
    public void testGetBrowserPreferencesReturnsAnEmptyNodeIfSpecifiedBrowserTypeIsNotPresentInConfigJson() throws JsonProcessingException {
        String preferenceKey = "browser.download.dir";
        String preferenceValue = "docs/chrome/";
        String inputConfigJson = String.format("{ \"browserPreferences\": { \"chrome\": {\"%s\": \"%s\"}}}", preferenceKey, preferenceValue);

        WebDriverConfig webDriverConfig = JsonUtils.fromString(inputConfigJson, WebDriverConfig.class);
        ObjectNode actualPreferences = webDriverConfig.getBrowserPreferences(BrowserType.SAFARI);
        assertThat(actualPreferences, is(JsonNodeFactory.instance.objectNode()));
    }

    @Test
    public void testGetBrowserPreferencesReturnsTheCorrectBrowserOptions() throws JsonProcessingException {
        String preferenceKey = "browser.download.dir";
        String preferenceValue = "docs/chrome/";
        String inputConfigJson = String.format("{ \"browserPreferences\": { \"chrome\": {\"%s\": \"%s\"}}}", preferenceKey, preferenceValue);

        WebDriverConfig webDriverConfig = JsonUtils.fromString(inputConfigJson, WebDriverConfig.class);
        ObjectNode actualPreferences = webDriverConfig.getBrowserPreferences(BrowserType.CHROME);
        ObjectNode expectedPreferences = JsonNodeFactory.instance.objectNode()
                .put(preferenceKey, preferenceValue);
        assertThat(actualPreferences, is(expectedPreferences));
    }

    @Test
    public void testGetBrowserPropertiesReturnsTheCorrectBrowserOptionsIrrespectiveOfCase() throws JsonProcessingException {
        String preferenceKey = "browser.download.dir";
        String preferenceValue = "docs/chrome/";
        String inputConfigJson = String.format("{ \"browserPreferences\": { \"CHROME\": {\"%s\": \"%s\"}}}", preferenceKey, preferenceValue);
        WebDriverConfig webDriverConfig = JsonUtils.fromString(inputConfigJson, WebDriverConfig.class);
        ObjectNode actualPreferences = webDriverConfig.getBrowserPreferences(BrowserType.CHROME);
        ObjectNode expectedPreferences = JsonNodeFactory.instance.objectNode()
                .put(preferenceKey, preferenceValue);    assertThat(actualPreferences, is(expectedPreferences));
    }

    @Test
    public void testConstructionFromJsonFileWithBadBaseUrlFails() {
        assertThrows(JsonMappingException.class, () -> JsonUtils.fromFile(
                ClassLoader.getSystemResourceAsStream("fixtures/sample-config-with-bad-base-url.json"),
                WebDriverConfig.class));
    }

    @Test
    public void testSettingBaseUrlWithBadUrlGivesGoodException() {
        WebDriverConfig webDriverConfig = new WebDriverConfig();
        assertThrows(MalformedURLException.class, () -> webDriverConfig.setBaseUrl("bad-url"));
    }
}
