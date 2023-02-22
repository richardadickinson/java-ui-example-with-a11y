package utils.webDriver.interactions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.NationalSearchPage;

import java.util.List;

import static utils.webDriver.Builder.getWebDriver;

/**
 * Methods for working with Search Results tables that match the layout of the National Search results table.
 * The number of columns and what they contain can change as long as the table conforms to the layout used by the xpaths
 * to locate the webelements in the table.
 */
public class SearchResultsTable {
    private final static Logger logger = LoggerFactory.getLogger(SearchResultsTable.class);

    /**
     * Selects the View link for the results row that corresponds to the match type and value supplied
     * tableId - the HTML id for the results table being used
     * matchType - the data type used to select the correct row e.g. 'CRN' or 'ContactId'
     * value - the value to be matched against e.g. 'X1234567'
     */
    public static void selectViewLinkFromSearchResults(String tableId, String matchType, String value) {
        String matchColumnIndex = getMatchColumnIndex(tableId, matchType);
        String viewLinkColumnIndex = getViewColumnIndex(tableId);

        if (!matchColumnIndex.equals("-1") && !viewLinkColumnIndex.equals("-1")) {
            List<WebElement> cells = getWebDriver().findElements(By.xpath("//table[@id='" + tableId + "']/tbody/tr/td[" + matchColumnIndex + "]"));
            logger.debug("Search results row count: " + cells.size());
            int rowIndex = 1;
            for (WebElement cell : cells) {
                if (cell.getText().contains(value)) {
                    logger.debug(matchType + " found, clicking View link...");
                    getWebDriver().findElement(By.xpath("//*[@id='" + tableId + "']/tbody/tr[" + rowIndex + "]/td[" + viewLinkColumnIndex + "]/a")).click();
                    break;
                }
                rowIndex++;
            }
            logger.debug("Search result row selected = " + rowIndex);
        } else {
            throw new RuntimeException("Search results table identifiers not recognised: tableId: " + tableId + " matchType: " + matchType);
        }
    }

    /**
     * Defines which column in a results table contains the values used for matching
     * This method will need to be updated for each results table required for testing
     */
    private static String getMatchColumnIndex(String tableId, String matchType) {
        String matchColumnIndex = "-1";
        if (tableId.equals(NationalSearchPage.getSearchResultsTableId())
                && matchType.equalsIgnoreCase("crn")) matchColumnIndex = "1";
        if (tableId.contains("testTable")) {
            if (matchType.equalsIgnoreCase("test")) matchColumnIndex = "7";
            if (matchType.equalsIgnoreCase("crn")) matchColumnIndex = "1";
        }
        return matchColumnIndex;
    }
    /**
     * Defines which column in a results table contains the View link
     * This method will need to be updated for each results table required for testing
     */
    private static String getViewColumnIndex(String tableId) {
        String viewLinkColumnIndex = "-1";
        if (tableId.equals(NationalSearchPage.getSearchResultsTableId())) viewLinkColumnIndex = "11";
        if (tableId.contains("testTable")) viewLinkColumnIndex = "9";
        return viewLinkColumnIndex;
    }
}
