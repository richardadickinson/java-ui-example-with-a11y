package utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.awaitility.Awaitility;
import org.awaitility.core.ConditionTimeoutException;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.webDriver.config.TolerantActionExceptions;
import utils.webDriver.interactions.TolerantInteraction;

import java.util.concurrent.Callable;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AsyncUtil {

    private static final Logger logger = LoggerFactory.getLogger(TolerantInteraction.class);
    private static final String waitTimeoutInSeconds = "5";

    public static void await(int atMost, Callable<Boolean> condition) {
        Awaitility.await().atMost(atMost, SECONDS).and().pollInterval(500, MILLISECONDS)
                .pollInSameThread()
                .until(condition);
    }

    public static void retryOnException(Runnable action) {
        try {
            action.run();
        } catch (ConditionTimeoutException | StaleElementReferenceException | NoSuchElementException |
                 TimeoutException | ElementNotInteractableException | AssertionError e) {
            logger.warn("Exception thrown: '{}' - retry will follow", e.getMessage());
            action.run();
        }
    }

    public static void retryOnExceptionUntil(Runnable action, int timeoutInSeconds) {
        Callable<Boolean> actionIsSuccessful = () -> {
            try {
                action.run();
                return true;
            } catch (ConditionTimeoutException | StaleElementReferenceException | NoSuchElementException
                     | TimeoutException | ElementNotInteractableException | AssertionError e) {
                logger.warn("Exception thrown: '{}'", e.getMessage());
                return false;
            }
        };

        await(timeoutInSeconds, actionIsSuccessful);
    }

    public static String getWaitTimeoutInSeconds() { return AsyncUtil.waitTimeoutInSeconds; }
}
