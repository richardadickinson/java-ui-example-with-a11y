package utils.webDriver.interactions;

import com.codahale.metrics.Timer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ByIdOrName;
import utils.AsyncUtil;
import utils.MetricRegistryHelper;

public class FindBy {
    private final static int defaultWaitTimeout = Integer.parseInt(AsyncUtil.getWaitTimeoutInSeconds());

    private static final Timer byIdOrNameAction = MetricRegistryHelper.get().timer("FindBy.findByIdOrName");

    public static void findByUntil(WebDriver webDriver, By by, int timeout) {
        Runnable findBy = () -> webDriver.findElement(by);
        AsyncUtil.retryOnExceptionUntil(findBy, timeout);
    }
    public static void findByUntil(WebDriver webDriver, By by) {
        findByUntil(webDriver, by, defaultWaitTimeout);
    }
    /**
     * tolerant find by
     */
    public static WebElement findByIdOrName(WebDriver webDriver, String idOrName) {
        try (final Timer.Context ignored = byIdOrNameAction.time()) {
            return webDriver.findElement(new ByIdOrName(idOrName));
        }
    }
}
