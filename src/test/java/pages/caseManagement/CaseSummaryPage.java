package pages.caseManagement;

import navigationPanel.MainNavigationPanelLinks;
import navigationPanel.caseManagementLinks.CaseManagementNavigationLinks;
import org.openqa.selenium.WebDriver;
import pages.BasePageObject;


public class CaseSummaryPage extends BasePageObject implements MainNavigationPanelLinks, CaseManagementNavigationLinks {

    private static final String expectedPageTitle = "Case Summary";

    public CaseSummaryPage(WebDriver webDriver) {
        super(webDriver);
        assertPageTitle(expectedPageTitle);
    }



}
