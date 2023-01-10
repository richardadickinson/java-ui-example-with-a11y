package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.WebDriverUtils;

public abstract class BasePageObject {

    private WebDriver webDriver = WebDriverUtils.getWebDriver();

    protected BasePageObject() {
        PageFactory.initElements(webDriver, this);
    }

}
