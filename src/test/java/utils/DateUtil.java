package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static String today() {
        DateTimeFormatter dft = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDateTime.now().format(dft);
    }

}
