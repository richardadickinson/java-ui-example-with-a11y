package utils.webDriver.configuredDrivers;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public interface ConfiguredDriver {

    WebDriver getDriver() throws IOException;
    <T> T getOptions() throws IOException;

    default String createFileDownloadDirectory(String path) throws IOException {
        String canonicalPath = new File(path).getCanonicalPath();
        FileUtils.forceMkdir(new File(canonicalPath));
        return canonicalPath;
    }
}
