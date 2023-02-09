package utils.webDriver.interactions;

import java.util.List;

public class TolerantExceptionHandler {
    private final List<String> tolerantExceptions;

    public TolerantExceptionHandler(List<String> tolerantExceptions) { this.tolerantExceptions = tolerantExceptions; }

    /**
     * Checks an input exception against a predefined list of tolerant exceptions.
     * exceptions on the tolerant list are RETURNED
     * exceptions not on the list are THROWN
     */
    public Throwable propagateExceptionIfNotIgnored(Throwable throwable) throws Throwable {
        for (String tolerantExceptionClassName : tolerantExceptions) {
            if (throwableIsTolerated(tolerantExceptionClassName, throwable)) {
                System.out.println(tolerantExceptionClassName + " exception will be ignored");
                return throwable;
            } else {
                System.out.println("Un-tolerated exception thrown during tolerant action attempt: " + throwable.getClass().getName());
            }
        }
        throw throwable;
    }

    private boolean throwableIsTolerated(String source, Throwable target) {
        return target.getClass().getName().contains(source);
    }
}