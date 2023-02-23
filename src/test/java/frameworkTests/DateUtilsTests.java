package frameworkTests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static utils.DateUtil.convertApiDate;
import static utils.DateUtil.getTodayDateFormatted;

public class DateUtilsTests {

    @DataProvider(name = "dates")
    public Object[][] dpMethod() {
        return new Object[][]{{"2000-11-17T00:00:00Z[UTC]"}, {"2020-01-05T10:15:30Z"}, {"1999-09-30T10:15:30+01:00[Europe/Paris]"}};
    }

    public boolean hasCorrectFormat(String dateStr, String dfp) {
        try {
            DateTimeFormatter.ofPattern(dfp).parse(dateStr);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    @Test
    public void testTodayDateIsReturnedWithCorrectFormat() {
        Assert.assertEquals(hasCorrectFormat(getTodayDateFormatted(), "dd/MM/yyyy"), true);
    }

    @Test
    public void testTodayDateDoesNotReturnIncorrectFormat() {
        Assert.assertEquals(hasCorrectFormat(getTodayDateFormatted(), "dd-MM-yyyy"), false);
    }

    @Test
    public void testTodayDateThrowsExceptionWhenParsedWithIncorrectFormat() {
        Assert.assertThrows(DateTimeParseException.class, () -> {
            LocalDate.parse(getTodayDateFormatted(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        });
    }

    @Test(dataProvider = "dates")
    public void testConvertedApiDateIsReturnedWithCorrectFormat(String date) {
        Assert.assertEquals(hasCorrectFormat(convertApiDate(date), "dd/MM/yyyy"), true);
    }

    @Test(dataProvider = "dates")
    public void testConvertedApiDateDoesNotReturnIncorrectFormat(String date) {
        Assert.assertEquals(hasCorrectFormat(convertApiDate(date), "dd-MM-yyyy"), false);
    }

    @Test
    public void testConvertedApiDateThrowsExceptionWhenDateFormatIsIncorrect() {
        Assert.assertThrows(DateTimeParseException.class, () -> {
            convertApiDate("2000/11/17T00:00:00Z[UTC]");
        });
    }

    @Test
    public void testConvertedApiDateThrowsExceptionWhenDateIsInvalid() {
        Assert.assertThrows(DateTimeParseException.class, () -> {
            convertApiDate("2008-11-45T00:00:00");
        });
    }



}
