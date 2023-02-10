package pages.approvedPremisesDiary;

import navigationPanel.ApprovedPremisesNavigationLinks;
import navigationPanel.MainNavigationPanelLinks;
import org.openqa.selenium.WebDriver;
import pages.BasePageObject;

public class ApprovedPremisesDiaryAwaitingArrivalPage extends BasePageObject implements MainNavigationPanelLinks, ApprovedPremisesNavigationLinks {

    private static final String expectedPageTitle = "Approved Premises Diary - Awaiting Arrival";

    public ApprovedPremisesDiaryAwaitingArrivalPage(WebDriver webDriver) {
        super(webDriver);
        assertPageTitle(expectedPageTitle);
    }
}
