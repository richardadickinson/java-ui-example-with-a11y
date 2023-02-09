package utils;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.jmx.JmxReporter;

public class MetricRegistryHelper {

    private static final MetricRegistry metricRegistry = new MetricRegistry();
    private static MetricRegistryHelper metricRegistryHelper;

    private MetricRegistryHelper() {
        try (JmxReporter jmxReporter = JmxReporter.forRegistry(metricRegistry).build()) {
            jmxReporter.start();
        }
    }

    public static MetricRegistry get() {
        if (null == metricRegistryHelper) {
            metricRegistryHelper = new MetricRegistryHelper();
        }
        return metricRegistry;
    }
}