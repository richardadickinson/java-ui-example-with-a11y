package pages.caseManagement.eventList;

import navigationPanel.MainNavigationPanelLinks;
import navigationPanel.caseManagementLinks.CaseManagementNavigationLinks;
import navigationPanel.caseManagementLinks.EventListNavigationLinks;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.BasePageObject;

import static stepDefinitions.BaseSteps.getSessionData;
import static utils.webDriver.Builder.getWebDriver;

public class EventDetailsPage extends BasePageObject implements MainNavigationPanelLinks, CaseManagementNavigationLinks, EventListNavigationLinks {

    private static final String expectedPageTitle = "Event Details";

    public EventDetailsPage(WebDriver webDriver) {
        super(webDriver);
        assertPageTitle(expectedPageTitle);
    }

    public void assertEventDetails() {
        String offenceCode = getSessionData().getEvent().getMainOffenceCode();
        Assert.assertTrue(getFieldLabelValue("Offence Sub-Cat").contains(offenceCode));
    }

    private String getFieldLabelValue(String labelName) {
        WebElement element = getWebDriver().findElement(By.xpath("//div[@class='form-group form-group-sm']/span[contains(text(), \"" + labelName
                + ":\")]/../div/span"));
        return element.getText();
    }

}
