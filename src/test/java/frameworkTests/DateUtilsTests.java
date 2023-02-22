package frameworkTests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static utils.DateUtil.convertApiDate;
import static utils.DateUtil.getTodayDateFormatted;

public class DateUtilsTests {

    @DataProvider(name = "formatted-dates")
    public Object[][] dpMethod() {
        return new Object[][]{{"2000-11-17T00:00:00Z[UTC]"}, {"2000-11-17T10:15:30Z"}, {"2000-11-17T10:15:30+01:00[Europe/Paris]"}};
    }

    @Test
    public void testTodayDateCorrectFormat() {
        String todayDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Assert.assertTrue(getTodayDateFormatted().equals(todayDate));
    }

    @Test
    public void testTodayDateIncorrectFormat() {
        String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        Assert.assertFalse(getTodayDateFormatted().equals(today));
    }

    @Test(dataProvider = "formatted-dates")
    public void testConvertApiDate(String date) {
        Assert.assertEquals(convertApiDate(date), "17/11/2000");
    }

}
