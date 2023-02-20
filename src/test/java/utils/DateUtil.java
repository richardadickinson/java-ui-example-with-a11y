package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class DateUtil {

    public static String today() {
        DateTimeFormatter dft = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDateTime.now().format(dft);
    }

    public static String convertApiDate(String apiDate)  {
        String filtered = Arrays.stream(apiDate.split("T")).toList().get(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(filtered, formatter);
        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(newFormatter);
    }

}
