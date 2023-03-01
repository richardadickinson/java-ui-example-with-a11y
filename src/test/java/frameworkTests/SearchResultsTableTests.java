package frameworkTests;

import org.testng.annotations.*;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static utils.webDriver.Builder.getWebDriver;
import static utils.webDriver.Builder.initialiseWebDriver;
import static utils.webDriver.interactions.SearchResultsTable.selectViewLinkFromSearchResults;

public class SearchResultsTableTests {

    private static EmbeddedJetty embeddedJetty;

    @BeforeClass
    public void setup() throws Exception {
        embeddedJetty = new EmbeddedJetty();
        embeddedJetty.start();
    }

    @BeforeMethod
    public void setupWebapp() throws IOException {
        String baseUrl = "http://localhost:" + embeddedJetty.getPort() + "/index.html";
        initialiseWebDriver();
        getWebDriver().get(baseUrl);
    }

    @AfterMethod
    public void teardownWebDriver() {
        getWebDriver().quit();
    }

    @AfterClass
    public void teardown() throws Exception {
        embeddedJetty.stop();
    }

    @DataProvider(name = "testData")
    public Object[][] data() {
        return new Object[][]{{"testTable", "X612449"}, {"testTable2", "X612420"}};
    }


    @Test(dataProvider = "testData")
    public void testSelectViewFromResultsTableByCRN(String tableId, String crn) {
        selectViewLinkFromSearchResults(tableId, "CRN", crn);
        assertThat(getWebDriver().getCurrentUrl(), is("http://localhost:"+embeddedJetty.getPort()+"/hello-passed.html"));
    }

    @Test(dataProvider = "testData")
    public void testSelectViewFromResultsTableByTestIdentifier(String tableId, String crn) {
        selectViewLinkFromSearchResults(tableId, "TEST", "Here I am");
        assertThat(getWebDriver().getCurrentUrl(), is("http://localhost:"+embeddedJetty.getPort()+"/hello-passed.html"));
    }

    @Test(expectedExceptions = {RuntimeException.class}, expectedExceptionsMessageRegExp = "Search results table identifiers not recognised: tableId: .* matchType: .*")
    public void testExceptionWhenSelectViewLinkFromSearchResultsFailsToMatchTableId() throws RuntimeException {
        selectViewLinkFromSearchResults("tableId", "BOGUS", "bunk");
    }

    @Test(expectedExceptions = {RuntimeException.class}, expectedExceptionsMessageRegExp = "Search results table identifiers not recognised: tableId: .* matchType: .*")
    public void testExceptionWhenSelectViewLinkFromSearchResultsFailsToMatchMatchType() throws RuntimeException {
        selectViewLinkFromSearchResults("testTable", "BOGUS", "bunk");
    }

}
