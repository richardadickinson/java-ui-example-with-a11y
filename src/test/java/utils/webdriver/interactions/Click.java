package utils.webdriver.interactions;

import com.codahale.metrics.Timer;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebElement;
import utils.AsyncUtil;
import utils.MetricRegistryHelper;

import java.util.Optional;

import static com.codahale.metrics.MetricRegistry.name;

public class Click extends TolerantInteraction {

    private final static int defaultWaitTimeout = Integer.parseInt(AsyncUtil.getWaitTimeoutInSeconds());

    private final static Timer tolerantClickAction = MetricRegistryHelper.get().timer(name("Click.tolerantClick"));

    public static void clickUntil(WebElement element, int timeout) {
        Runnable click = () -> {
            if (Boolean.TRUE.equals(element.isEnabled())) {
                element.click();
            } else {
                throw new ElementNotInteractableException("Element is disabled");
            }
        };
        AsyncUtil.retryOnExceptionUntil(click, timeout);
    }
    public static void clickUntil(WebElement webElement) {
        clickUntil(webElement, defaultWaitTimeout);
    }

    public static void clickWithRetry(WebElement element) {
        Runnable click = () -> {
            if (Boolean.TRUE.equals(element.isEnabled())) {
                element.click();
            } else {
                throw new ElementNotInteractableException("Element is disabled");
            }
        };
        AsyncUtil.singleRetryOnException(click);
    }

    /**
     * TolerantClick
     */
    public static void tolerantClick (WebElement webElement, int timeout) throws Throwable {
        try (final Timer.Context ignored = tolerantClickAction.time()) {
            new Click().tolerantInteraction(webElement, Optional.empty(), timeout);
        }
    }
    public static void tolerantClick(WebElement webElement) throws Throwable {
        tolerantClick(webElement, defaultWaitTimeout);
    }
}