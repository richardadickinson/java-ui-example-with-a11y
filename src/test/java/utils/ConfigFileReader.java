package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class ConfigFileReader {

    private static final Logger logger = LoggerFactory.getLogger(ConfigFileReader.class);

    /**
     *  Look for a file to load on a fully qualified path, or the classpath
     *  @param path location to look for file (either relative or classpath)
     *  @return File the file that represents the found file on either the path or the classpath
     */
    public static File loadFromClasspathOrFileSystem(String path) throws IOException {
        File file = new File(path);
        if (file.isFile()) {
            logger.info("Using configuration file {}", file.getAbsolutePath());
            return file;
        } else {
            try {
                File fromFile = new File(ClassLoader.getSystemResource(path).getPath());
                logger.info("Using configuration file {}", fromFile.getAbsolutePath());
                return fromFile;
            } catch(NullPointerException e) {
                throw new IOException("File could not be found: " + path);
            }
        }
    }

}