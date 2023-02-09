package utils.webDriver.interactions;

import com.codahale.metrics.Timer;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.AsyncUtil;
import utils.MetricRegistryHelper;
import utils.webdriver.config.SelectBoxInteractionType;
import utils.webdriver.interactions.TolerantInteraction;

import java.util.Optional;

import static com.codahale.metrics.MetricRegistry.name;

public class SelectBox extends TolerantInteraction {

    private final static Timer tolerantItemByIndexAction = MetricRegistryHelper.get().timer(name("SelectBoxUtils.tolerantItemByIndex"));

    private final static int defaultTolerantWaitTimeout = Integer.parseInt(AsyncUtil.getWaitTimeoutInSeconds());

    public static void selectItemByIndex(WebElement selectBox, int index) {
        int normalisedIndex = index - 1;
        Select select = new Select(selectBox);
        select.selectByIndex(normalisedIndex);
    }

    public static void selectItemByIndexWithRetry(WebElement selectBox, int index, int timeout) {
        Runnable selectByIndex = () -> {
            int normalisedIndex = index - 1;
            Select select = new Select(selectBox);
            select.selectByIndex(normalisedIndex);
        };
        AsyncUtil.retryOnExceptionUntil(selectByIndex, timeout);
    }
    public static void selectItemByIndexWithRetry(WebElement webElement, int index) {
        selectItemByIndexWithRetry(webElement, index, defaultTolerantWaitTimeout);
    }

    /**
     * Tolerant exception handling as per Evoco framework.
     * It stays here until we are sure we don't want it - depends on how well awaitility works
     */
    public static void tolerantItemByIndex(WebElement webElement, int index, int timeout) throws Throwable {
        try (final Timer.Context ignored = tolerantItemByIndexAction.time()) {
            new SelectBox().tolerantInteraction(
                    webElement, SelectBoxInteractionType.BY_INDEX, Optional.empty(), Optional.of(index), timeout);
        }
    }
    public static void tolerantItemByIndex(WebElement webElement, int index) throws Throwable {
        tolerantItemByIndex(webElement, index, defaultTolerantWaitTimeout);
    }

}