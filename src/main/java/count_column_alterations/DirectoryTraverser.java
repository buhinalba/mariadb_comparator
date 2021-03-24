package count_column_alterations;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class DirectoryTraverser {
    String DIRECTORY_PATH;
    SQLFileReader sqlFileReader;


    public DirectoryTraverser(String DIRECTORY_PATH) {
        this.DIRECTORY_PATH = DIRECTORY_PATH;
        sqlFileReader = new SQLFileReader();

    }

    /**
     * traverses directory and its subdirectories looking for .sql files,
     * and looks for column alterations in them
     * @throws Exception for example if the path String is malformed, or non existent.
     */
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
