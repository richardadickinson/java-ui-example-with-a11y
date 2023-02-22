package utils.webDriver.interactions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.NationalSearchPage;

import java.util.List;

import static utils.webDriver.Builder.getWebDriver;

public class SearchResultsTable {
    private final static Logger logger = LoggerFactory.getLogger(SearchResultsTable.class);

    public static void selectSearchResultsTableViewLink(String tableId, String matchType, String value) {
        String matchColumnIndex="0";
        String viewLinkColumnIndex="0";
        if (matchType.equalsIgnoreCase("crn")) {
            matchColumnIndex = "1";
        }
        if (tableId.equalsIgnoreCase(NationalSearchPage.getSearchResultsTableId())) {
            viewLinkColumnIndex = "11";
        }

        List<WebElement> cells = getWebDriver().findElements(By.xpath("//table[@id='"+ tableId + "']/tbody/tr/td["+matchColumnIndex+"]"));
        logger.debug("Search results row count: " + cells.size());
        int rowIndex = 1;
        for (WebElement cell: cells) {
            if (cell.getText().contains(value)) {
                logger.debug(matchType + " found, clicking View link...");
                getWebDriver().findElement(By.xpath("//*[@id='"+ tableId +"']/tbody/tr["+rowIndex+"]/td["+viewLinkColumnIndex+"]/a")).click();
                break;
            }
            rowIndex++;
        }
        logger.debug("Search result row selected = " + rowIndex);
    }
}
