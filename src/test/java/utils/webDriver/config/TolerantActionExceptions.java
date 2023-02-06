package utils.webDriver.config;

import java.util.Arrays;
import java.util.List;

public class TolerantActionExceptions {

    private static final String waitTimeoutInSeconds = "5";
    private static final List<String> exceptionsToHandle = Arrays.asList("StaleElementReferenceException",
                                                            "ElementClickInterceptedException",
                                                            "ElementNotInteractableException");

    public static String getWaitTimeoutInSeconds() { return waitTimeoutInSeconds; }

    public static List<String> getExceptionsToHandle() { return exceptionsToHandle; }

}
