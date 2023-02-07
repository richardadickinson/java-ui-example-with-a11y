package utils;

import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import com.deque.html.axecore.selenium.AxeBuilder;
import com.deque.html.axecore.selenium.AxeReporter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.commons.io.FileUtils;
import org.testng.Assert;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static utils.webDriver.Builder.getWebDriver;

public class AxeA11y {
    @SuppressWarnings("JavadocLinkAsPlainText")
    private static final String reportPath = System.getProperty("user.dir") + "/target/axe-reports/";

    /**
     * Method to Analyze the current page for A11y violations and save a report to file.
     *
     * To Restrict the assessment to certain A11y rules (or tags):
     * 1. define a list of the required rules:
     *          private static List<String> tags = Arrays.asList("wcag2a", "wcag2aa");
     *    The full list of valid rules/tags can be seen here:
     *          https://www.deque.com/axe/core-documentation/api-documentation/#axe-core-tags
     *
     * 2. add one of the following methods in the AxeBuilder
     *    (the second method called will override the first if both are used)
     *          builder.withTags(tags);
     *          builder.withRules(tags);
     */
    public static int checkAccessibilityViolations(String pageName) throws IOException {
        AxeBuilder builder = new AxeBuilder();
        Results results = builder.analyze(getWebDriver());
        saveReport(results, reportPath, pageName);
        return results.getViolations().size();
    }

    public static void saveReport(Results results, String reportPath, String pageName) throws IOException {
        List<Rule> violations = results.getViolations();
        if (violations.size() == 0)
        {
            Assert.assertTrue(true, "No A11y violations found");
        }
        else
        {
            String reportFile = reportPath + pageName + "A11yReport";
            File directory = new File(reportPath);
            if (!directory.exists()) { FileUtils.forceMkdir(directory); }
            AxeReporter.writeResultsToJsonFile(reportFile, results);
            saveTextReport(reportFile);
        }
    }

    // To format the report to a text file (e.g. for readability) call this method from saveReport():
    public static void saveTextReport(String reportFile) throws FileNotFoundException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement jsonElement = JsonParser.parseReader(new FileReader(reportFile + ".json"));
        String prettyJson = gson.toJson(jsonElement);
        AxeReporter.writeResultsToTextFile(reportFile, prettyJson);
    }
}
