package pages.officerDiary;

import navigationPanel.MainNavigationPanelLinks;
import navigationPanel.OfficerDiaryNavigationLinks;
import org.openqa.selenium.WebDriver;
import pages.BasePageObject;

public class CaseloadDiaryPage extends BasePageObject implements MainNavigationPanelLinks, OfficerDiaryNavigationLinks {

    private static final String expectedPageTitle = "Caseload Diary";

    public CaseloadDiaryPage(WebDriver webDriver) {
        super(webDriver);
        assertPageTitle(expectedPageTitle);
    }
}
