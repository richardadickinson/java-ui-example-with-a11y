package pages.approvedpremisesDiary;

import navigationpanel.ApprovedPremisesNavigationLinks;
import navigationpanel.MainNavigationPanelLinks;
import org.openqa.selenium.WebDriver;
import pages.BasePageObject;

public class ApprovedPremisesDiaryAwaitingArrivalPage extends BasePageObject implements MainNavigationPanelLinks, ApprovedPremisesNavigationLinks {

    private static final String expectedPageTitle = "Approved Premises Diary - Awaiting Arrival";

    public ApprovedPremisesDiaryAwaitingArrivalPage(WebDriver webDriver) {
        super(webDriver);
        assertPageTitle(expectedPageTitle);
    }
}
