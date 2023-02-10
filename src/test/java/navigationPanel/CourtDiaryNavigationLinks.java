package navigationPanel;

import pages.courtDiary.CourtDiaryAppearancesPage;
import pages.courtDiary.CourtDiaryBreachesPage;
import pages.courtDiary.CourtDiaryCommittalsPage;

import static pages.BasePageObject.clickOnLinkViaText;
import static utils.webDriver.Builder.getWebDriver;

/**
 * This interface contains a list of method returning the appropriate pageObject by clicking on navigation links that become visible
 * under the Court Diary section of the main navigation panel.
 * Classes that implement this interface should ONLY be pageObjects from which these links are visible. e.g. Court Diary - Appearances Page
 */
public interface CourtDiaryNavigationLinks {

    default CourtDiaryAppearancesPage clickOnAppearancesLink() {
        clickOnLinkViaText("Appearances");
        return new CourtDiaryAppearancesPage(getWebDriver());
    }

    default CourtDiaryCommittalsPage clickOnCommittalsLink() {
        clickOnLinkViaText("Committals");
        return new CourtDiaryCommittalsPage(getWebDriver());
    }

    default CourtDiaryBreachesPage clickOnBreachesLink() {
        clickOnLinkViaText("Breaches");
        return new CourtDiaryBreachesPage(getWebDriver());
    }


}
