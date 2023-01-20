package utils.dbUtils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.charset.StandardCharsets.UTF_8;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SqlFileReader {

    private static final String SQL_RESOURCES_DIR = "src/test/resources/sql/";

    public static String readContentsOfSqlFile(String filename) {
        Path path = Paths.get(SQL_RESOURCES_DIR, filename);
        return readFileToString(path);
    }

    public static String readContentsOfSqlFile(String dir, String filename) {
        Path path = Paths.get(SQL_RESOURCES_DIR, dir, filename);
        return readFileToString(path);
    }

    private static String readFileToString(Path path) {
        File file = new File(path.toUri());
        try {
            return FileUtils.readFileToString(file, UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readFileAndConvertToString(String fileNane) {
        StringBuilder sql = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(SQL_RESOURCES_DIR+fileNane))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sql.append(line);
                sql.append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sql.toString();
    }

}
