package pages;

import static pages.PageNavigation.HOME_PAGE;
import static pages.PageNavigation.NATIONAL_SEARCH;

public class HomePage extends BasePageObject {

    public HomePage() {
        super(HOME_PAGE);
    }

    public NationalSearchPage gotToNationalSearch() {
        clickOnLinkViaText(NATIONAL_SEARCH);
        return new NationalSearchPage();
    }


}
