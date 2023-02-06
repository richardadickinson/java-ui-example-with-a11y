package utils.webDriver.interactions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.MetricRegistryHelper;

import com.codahale.metrics.Timer;
import utils.webDriver.config.SelectBoxInteractionType;
import utils.webDriver.config.TolerantActionExceptions;

import java.util.Optional;

import static com.codahale.metrics.MetricRegistry.name;

public class SelectBoxUtils extends TolerantInteraction {

    private final static Timer itemByIndexAction = MetricRegistryHelper.get().timer(name("SelectBoxUtils.itemByIndex"));
    private final static Timer tolerantItemByIndexAction = MetricRegistryHelper.get().timer(name("SelectBoxUtils.tolerantItemByIndex"));

    private final static int defaultTolerantWaitTimeout = Integer.parseInt(TolerantActionExceptions.getWaitTimeoutInSeconds());

    public static void itemByIndex(WebElement selectBox, int index) {
        try (final Timer.Context ignored = itemByIndexAction.time()) {
            int normalisedIndex = index - 1;
            Select select = new Select(selectBox);
            select.selectByIndex(normalisedIndex);
        }
    }
    public static void tolerantItemByIndex(WebElement webElement, int index, int timeout) throws Throwable {
        try (final Timer.Context ignored = tolerantItemByIndexAction.time()) {
            new SelectBoxUtils().tolerantInteraction(
                    webElement, SelectBoxInteractionType.BY_INDEX, Optional.empty(), Optional.of(index), timeout);
        }
    }
    public static void tolerantItemByIndex(WebElement webElement, int index) throws Throwable {
        tolerantItemByIndex(webElement, index, defaultTolerantWaitTimeout);
    }
}
