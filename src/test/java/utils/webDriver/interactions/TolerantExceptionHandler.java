package utils.webDriver.interactions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TolerantExceptionHandler {
    private final List<String> tolerantExceptions;
    private Logger logger = LoggerFactory.getLogger(TolerantExceptionHandler.class);

    public TolerantExceptionHandler(List<String> tolerantExceptions) { this.tolerantExceptions = tolerantExceptions; }
    public TolerantExceptionHandler(List<String> tolerantExceptions, Logger logger) {
        this.tolerantExceptions = tolerantExceptions;
        this.logger = logger;
    }

    /**
     * Checks an input exception against a predefined list of tolerant exceptions.
     * exceptions on the tolerant list are RETURNED
     * exceptions not on the list are THROWN
     */
    public Throwable propagateExceptionIfNotIgnored(Throwable throwable) throws Throwable {
        for (String tolerantExceptionClassName : tolerantExceptions) {
            if (throwableIsTolerated(tolerantExceptionClassName, throwable)) {
                logger.info( "Tolerated {} exception will be ignored", tolerantExceptionClassName);
                return throwable;
            } else {
                logger.error("Prohibited exception thrown during tolerant action attempt: {}", throwable.getClass().getName());
            }
        }
        throw throwable;
    }

    private boolean throwableIsTolerated(String source, Throwable target) {
        return target.getClass().getName().contains(source);
    }
}
