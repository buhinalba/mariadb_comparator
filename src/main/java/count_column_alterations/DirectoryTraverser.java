package count_column_alterations;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectoryTraverser {
    String DIRECTORY_PATH;


    public DirectoryTraverser(String DIRECTORY_PATH) {
        this.DIRECTORY_PATH = DIRECTORY_PATH;
    }


    public void traverse() throws Exception {
        traverse(DIRECTORY_PATH);
    }


    private void traverse(String directoryPath) throws IOException {
        Path dir = Paths.get(directoryPath);
        Files.walk(dir).forEach(path -> {
            if (path.toString().matches(".+/\\..+") || path.equals(dir)){
                return;
            }
            lookForSQLFiles(path.toFile());
        });
    }


    private void lookForSQLFiles(File file) {
        if (file.isDirectory()) {
            System.out.println("Directory: " + file.getAbsolutePath());
            try {
                if (file.getAbsolutePath().matches(".+/\\..+")) {
                    return;
                }
                traverse(file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // todo check if .sql -> if true, look through it
            System.out.println("File: " + file.getAbsolutePath());
        }
    }


}
