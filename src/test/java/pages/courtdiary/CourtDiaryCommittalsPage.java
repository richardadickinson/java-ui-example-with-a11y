package pages.courtdiary;

import navigationpanel.CourtDiaryNavigationLinks;
import navigationpanel.MainNavigationPanelLinks;
import org.openqa.selenium.WebDriver;
import pages.BasePageObject;

public class CourtDiaryCommittalsPage extends BasePageObject implements MainNavigationPanelLinks, CourtDiaryNavigationLinks {

    private static final String expectedPageTitle = "Court Diary - Committals";

    public CourtDiaryCommittalsPage(WebDriver webDriver) {
        super(webDriver);
        assertPageTitle(expectedPageTitle);
    }
}
