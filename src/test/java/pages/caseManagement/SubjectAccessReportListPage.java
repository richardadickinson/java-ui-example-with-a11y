package pages.caseManagement;

import navigationPanel.MainNavigationPanelLinks;
import navigationPanel.caseManagementLinks.CaseManagementNavigationLinks;
import org.openqa.selenium.WebDriver;
import pages.BasePageObject;

public class SubjectAccessReportListPage extends BasePageObject implements MainNavigationPanelLinks, CaseManagementNavigationLinks {

    private static final String expectedPageTitle = "Subject Access Report List";

    public SubjectAccessReportListPage(WebDriver webDriver) {
        super(webDriver);
        assertPageTitle(expectedPageTitle);
    }
}
