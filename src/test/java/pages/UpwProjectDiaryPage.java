package pages;

import navigationpanel.MainNavigationPanelLinks;
import org.openqa.selenium.WebDriver;

public class UpwProjectDiaryPage extends BasePageObject implements MainNavigationPanelLinks {

    private static final String expectedPageTitle = "UPW Project Diary";

    public UpwProjectDiaryPage(WebDriver webDriver) {
        super(webDriver);
        assertPageTitle(expectedPageTitle);
    }
}
