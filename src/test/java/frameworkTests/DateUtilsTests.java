package frameworkTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static utils.DateUtil.convertApiDate;
import static utils.DateUtil.today;

public class DateUtilsTests {


    @Test
    public void testTodayDateCorrectFormat(){
        String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Assert.assertTrue(today().equals(today));
    }

    @Test
    public void testTodayDateIncorrectFormat(){
        String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        Assert.assertFalse(today().equals(today));
    }


    @Test
    public void testConvertApiDateCorrectFormat() {
        String apiDate = "2022-12-22T10:23:07.712Z[UTC]";
        Assert.assertTrue(convertApiDate(apiDate).equals("22/12/2022"));
    }

    @Test
    public void testConvertApiDateIncorrectFormat() {
        String apiDate = "2022-12-22T10:23:07.712Z[UTC]";
        Assert.assertFalse(convertApiDate(apiDate).equals("22-12-2022"));
    }


}
