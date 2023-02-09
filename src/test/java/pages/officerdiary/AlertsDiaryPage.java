package pages.officerdiary;

import navigationpanel.MainNavigationPanelLinks;
import navigationpanel.OfficerDiaryNavigationLinks;
import org.openqa.selenium.WebDriver;
import pages.BasePageObject;

public class AlertsDiaryPage extends BasePageObject implements MainNavigationPanelLinks, OfficerDiaryNavigationLinks {

    private static final String expectedPageTitle = "Alerts Diary";
    public AlertsDiaryPage(WebDriver webDriver) {
        super(webDriver);
        assertPageTitle(expectedPageTitle);
    }
}
