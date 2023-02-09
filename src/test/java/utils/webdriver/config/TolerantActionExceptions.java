package utils.webdriver.config;

import java.util.Arrays;
import java.util.List;

public class TolerantActionExceptions {

    private static final List<String> exceptionsToHandle = Arrays.asList("NoSuchElementException",
            "StaleElementReferenceException",
            "ElementClickInterceptedException",
            "ElementNotInteractableException");

    public static List<String> getExceptionsToHandle() { return exceptionsToHandle; }

}