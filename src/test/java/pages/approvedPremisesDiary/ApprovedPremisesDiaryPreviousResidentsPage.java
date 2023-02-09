package pages.approvedPremisesDiary;

import navigationPanel.ApprovedPremisesNavigationLinks;
import navigationPanel.MainNavigationPanelLinks;
import org.openqa.selenium.WebDriver;
import pages.BasePageObject;

public class ApprovedPremisesDiaryPreviousResidentsPage extends BasePageObject implements MainNavigationPanelLinks, ApprovedPremisesNavigationLinks {

    private static final String expectedPageTitle = "Approved Premises Diary - Previous Residents";

    public ApprovedPremisesDiaryPreviousResidentsPage(WebDriver webDriver) {
        super(webDriver);
        assertPageTitle(expectedPageTitle);
    }
}
