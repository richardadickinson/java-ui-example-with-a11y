package navigationPanel.caseManagementLinks;

import pages.caseManagement.personalDetails.AdditionalIdentifiersPage;

import static pages.BasePageObject.clickOnLinkViaText;
import static utils.webDriver.Builder.getWebDriver;

/**
 * This interface contains a list of method returning the appropriate pageObject by clicking on navigation links that become visible
 * under the "Personal Details" subsection of the "Case Management" section.
 * Classes that implement this interface should ONLY be pageObjects from which these links are visible. e.g. "Personal Details" Page
 */
public interface PersonalDetailsNavigationLinks {

    default AdditionalIdentifiersPage clickOnAdditionalIdentifiersLink() {
        clickOnLinkViaText("Additional Identifiers");
        return new AdditionalIdentifiersPage(getWebDriver());
    }

}
