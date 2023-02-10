package navigationPanel;

import pages.messageAdministration.MessageAdministrationPage;
import pages.messageAdministration.SpgMessageErrorsPage;

import static pages.BasePageObject.clickOnLinkViaText;
import static utils.webDriver.Builder.getWebDriver;

/**
 * This interface contains a list of method returning the appropriate pageObject by clicking on navigation links that become visible
 * under the "Message Administration" section of the main navigation panel.
 * Classes that implement this interface should ONLY be pageObjects from which these links are visible. e.g. "Message Administration" Page
 */
public interface MessageAdministrationNavigationLinks {

    default MessageAdministrationPage clickMessageAdministrationLink() {
        clickOnLinkViaText("Message Administration");
        return new MessageAdministrationPage(getWebDriver());
    }

    default SpgMessageErrorsPage clickSpsMessageErrorsLink() {
        clickOnLinkViaText("SPG Message Errors");
        return new SpgMessageErrorsPage(getWebDriver());
    }


}
