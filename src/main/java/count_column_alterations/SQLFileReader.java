package count_column_alterations;

import java.io.File;
import java.util.ArrayList;

public class SQLFileReader {
    ArrayList<String> alteredColumns;


    public ArrayList<String> getAlteredColumns(File file) {
        // todo read through lines
        return null;
    }


    public SQLFileReader(ArrayList<String> alteredColumns) {
        this.alteredColumns = alteredColumns;
    }


    public SQLFileReader() {
    }
}
