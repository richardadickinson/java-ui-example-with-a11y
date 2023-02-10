package pages.caseManagement.drugs;

import navigationPanel.MainNavigationPanelLinks;
import navigationPanel.caseManagementLinks.CaseManagementNavigationLinks;
import navigationPanel.caseManagementLinks.DrugsNavigationLinks;
import org.openqa.selenium.WebDriver;
import pages.BasePageObject;

public class DrugsHistoryPage extends BasePageObject implements MainNavigationPanelLinks, CaseManagementNavigationLinks, DrugsNavigationLinks {

    private static final String expectedPageTitle = "Drugs History";

    public DrugsHistoryPage(WebDriver webDriver) {
        super(webDriver);
        assertPageTitle(expectedPageTitle);
    }
}
