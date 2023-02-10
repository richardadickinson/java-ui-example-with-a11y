package utils.webDriver.interactions;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Sleeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.webDriver.config.SelectBoxInteractionType;
import utils.webDriver.config.TolerantActionExceptions;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

public class TolerantInteraction {

    private final Logger logger = LoggerFactory.getLogger(TolerantInteraction.class);
    private final Clock clock = Clock.systemDefaultZone();
    private Instant end;
    private final Sleeper sleeper = Sleeper.SYSTEM_SLEEPER;
    private final Duration intervalDuration = Duration.ofMillis(500);
    private Throwable lastException = null;
    private final TolerantExceptionHandler tolerantExceptionHandler = new TolerantExceptionHandler(
            TolerantActionExceptions.getExceptionsToHandle(), logger);

    public WebElement tolerantInteraction(WebElement webElement, Optional<String> textToType, int timeoutSeconds) throws Throwable {
        end = clock.instant().plusSeconds(timeoutSeconds);
        while (true) {
            try {
                if (Boolean.TRUE.equals(webElement.isEnabled())) {
                    textToType.ifPresentOrElse(
                            text -> interact(webElement, text),
                            () -> interact(webElement)
                    );
                    return webElement;
                } else {
                    logger.debug("'" + webElement.getAccessibleName() + "' is not enabled for interaction");
                }
            } catch (Throwable e) {
                lastException = tolerantExceptionHandler.propagateExceptionIfNotIgnored(e);
            }
            checkTimeout(timeoutSeconds);
        }
    }

    public WebElement tolerantInteraction(
            WebElement webElement,
            SelectBoxInteractionType selectBoxInteractionType,
            Optional<String> visibleTextOrHtmlValueString,
            Optional<Integer> itemIndex,
            int timeoutInSeconds
    ) throws Throwable {
        end = clock.instant().plusSeconds(timeoutInSeconds);
        while(true) {
            try {
                if (interactWithSelectBox(visibleTextOrHtmlValueString, itemIndex, webElement, selectBoxInteractionType)) {
                    return webElement;
                }
            } catch (Throwable e) {
                lastException = tolerantExceptionHandler.propagateExceptionIfNotIgnored(e);
            }
            checkTimeout(timeoutInSeconds);
        }
    }

    private void interact(WebElement webElement) { webElement.click(); }

    private void interact(WebElement webElement, String text) { webElement.sendKeys(text); }

    private boolean interactWithSelectBox(
            Optional<String> visibleTextOrHtmlValueString,
            Optional<Integer> itemIndex,
            WebElement webElement,
            SelectBoxInteractionType selectBoxInteractionType) {
        if(Boolean.TRUE.equals(webElement.isEnabled())) {
            visibleTextOrHtmlValueString.ifPresent(
                    text -> interactWithSelectBoxByType(new Select(webElement), text, selectBoxInteractionType)
            );
            itemIndex.ifPresent(index -> new Select(webElement).selectByIndex(index -1));
            return true;
        }
        return false;
    }

    private void interactWithSelectBoxByType(Select selectBox, String value, SelectBoxInteractionType selectBoxInteractionType) {
        switch (selectBoxInteractionType) {
            case BY_VALUE -> selectBox.selectByValue(value);
            case BY_VISIBLE_TEXT -> selectBox.selectByVisibleText(value);
            default -> throw new WebDriverException("Select box interaction type must be BY_VALUE or BY_VISIBLE_TEXT");
        }
    }


    private void checkTimeout(int timeoutInSeconds) throws Throwable {
        if (end.isBefore(clock.instant())) {
            if (null == lastException) {
                logger.error(
                        "Exception condition failed: Timeout (tried for {} seconds with 500ms interval)",
                        timeoutInSeconds);
                lastException = new TimeoutException();
            } else {
                logger.error("Exception condition failed: {} (tried for {} seconds with 500ms interval)",
                        lastException.getCause(), timeoutInSeconds);
            }
            throw lastException;
        }

        try {
            sleeper.sleep(intervalDuration);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new WebDriverException(e);
        }
    }
}
