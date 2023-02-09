package pages.approvedpremisesDiary;

import navigationpanel.ApprovedPremisesNavigationLinks;
import navigationpanel.MainNavigationPanelLinks;
import org.openqa.selenium.WebDriver;
import pages.BasePageObject;

public class ApprovedPremisesDiaryCurrentResidentsPage extends BasePageObject implements MainNavigationPanelLinks, ApprovedPremisesNavigationLinks {

    private static final String expectedPageTitle = "Approved Premises Diary - Current Residents";

    public ApprovedPremisesDiaryCurrentResidentsPage(WebDriver webDriver) {
        super(webDriver);
        assertPageTitle(expectedPageTitle);
    }
}
