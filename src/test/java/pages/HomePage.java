package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class HomePage {


    @FindBy(css = "title")
    private WebElement pageTitle;


    public void assertPageTitle(){
        Assert.assertEquals(pageTitle.getText(), "National Delius Home Page");
    }


}
