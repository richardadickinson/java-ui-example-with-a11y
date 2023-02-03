package utils.webDriver.config;

import java.util.Arrays;
import java.util.List;

public class TolerantActionExceptions {

    private final String waitTimeoutInSeconds = "5";
    private final List<String> exceptionsToHandle = Arrays.asList("StaleElementReferenceException",
                                                            "ElementClickInterceptedException",
                                                            "ElementNotInteractableException");

    public String getWaitTimeoutInSeconds() { return waitTimeoutInSeconds; }

    public List<String> getExceptionsToHandle() { return exceptionsToHandle; }

}
