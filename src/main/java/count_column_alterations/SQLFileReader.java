package count_column_alterations;

import java.io.File;
import java.util.ArrayList;

public class SQLFileReader {
    ArrayList<String> alteredColumns;


    public ArrayList<String> readAlteredColumns(File file) {
        System.out.println(file.getAbsolutePath());
        // todo read through lines
        // -> check for ALTER COLUMN
        return null;
    }

    public SQLFileReader() {
    }

    public ArrayList<String> getAlteredColumns() {
        return alteredColumns;
    }
}
