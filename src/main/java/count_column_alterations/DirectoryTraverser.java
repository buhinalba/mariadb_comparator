package count_column_alterations;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class DirectoryTraverser {
    String DIRECTORY_PATH;
    SQLFileReader sqlFileReader;


    public DirectoryTraverser(String DIRECTORY_PATH) {
        this.DIRECTORY_PATH = DIRECTORY_PATH;
        sqlFileReader = new SQLFileReader();

    }


    public void traverse() throws Exception {
        Path dir = Paths.get(DIRECTORY_PATH);
        Files.walk(dir).forEach(path -> {
            if (path.toString().matches(".+\\.sql")) sqlFileReader.readAlteredColumns(path.toFile());
        });
    }


    public void printResult() {
        for (String column: sqlFileReader.getAlteredColumns()) {
            System.out.println(column);
        }
    }

}
