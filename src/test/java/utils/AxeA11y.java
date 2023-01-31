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
//import java.util.Arrays;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static utils.WebDriverUtils.getWebDriver;

public class AxeA11y {

    /** To restrict the assessment to specific accessibility rules you must pass in the rules you want to use in a list
     * The full list of valid rules/tags can be seen here: https://www.deque.com/axe/core-documentation/api-documentation/#axe-core-tags */
    //private static List<String> tags = Arrays.asList("wcag2a", "wcag2aa");
    private static final String reportPath = System.getProperty("user.dir") + "/target/axe-reports/";

    public static int checkAccessibilityViolations(String pageName) throws IOException {
        AxeBuilder builder = new AxeBuilder();
        /** Restrict assessment to set rules (or tags) - only one of the following methods can be used at a time
         * - the second one will override the first if there are two */
        //builder.withTags(tags);
        //builder.withRules(tags);
        Results results = builder.analyze(getWebDriver());
        saveReport(results, reportPath, pageName);
        return results.getViolations().size();
    }

    public static void saveReport(Results results, String reportPath, String pageName) throws IOException {
        List<Rule> violations = results.getViolations();
        if (violations.size() == 0)
        {
            Assert.assertTrue(true, "No violations found");
        }
        else
        {
            String reportFile = reportPath + pageName + "A11yReport";
            File directory = new File(reportPath);
            if (!directory.exists()) { FileUtils.forceMkdir(directory); }
            AxeReporter.writeResultsToJsonFile(reportFile, results);
        }
    }

    // To format the report to a text file (e.g. for readability) call this method from saveReport():
    public static void saveTextReport(String reportFile) throws FileNotFoundException {
        JsonParser jsonParser = new JsonParser();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement jsonElement = jsonParser.parse(new FileReader(reportFile + ".json"));
        String prettyJson = gson.toJson(jsonElement);
        AxeReporter.writeResultsToTextFile(reportFile, prettyJson);
    }
}
