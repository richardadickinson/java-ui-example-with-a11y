package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class DateUtil {

    public static String getTodayDateFormatted() {
        DateTimeFormatter dft = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDateTime.now().format(dft);
    }

    public static String convertApiDate(String apiDate)  {
        String filteredDate = Arrays.stream(apiDate.split("T")).toList().get(0);
        DateTimeFormatter sourceFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate originalFormattedDate = LocalDate.parse(filteredDate, sourceFormat);
        DateTimeFormatter newFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return originalFormattedDate.format(newFormat);
    }

}
