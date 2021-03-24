package count_column_alterations;

import java.io.*;
import java.util.ArrayList;

public class SQLFileReader {
    ArrayList<String> alteredColumns;


    /**
     * reads sql file line-by-line. If it finds an ALTER COLUMN command, it adds the table name to the alteredColumns list.
     * @param file file to look for ddl (ALTER COLUMN) commands in
     */
    public void readAlteredColumns(File file){
        System.out.println(file.getAbsolutePath());
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
            String line = reader.readLine();
            while (line != null) {
                addAlteredColumnIfExists(line);
                // read next line
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("File does not exist: " + file.getAbsolutePath());
        }
    }


    public SQLFileReader() {
        alteredColumns = new ArrayList<>();
    }


    /**
     * Gets column name if altered in query in a table.column format and adds it to the alteredColumns list.
     * @param query sql query
     */
    private void addAlteredColumnIfExists(String query) {
        String[] queryParts = query.split(" ALTER COLUMN ");
        if (queryParts.length == 2) {
            String tableName = queryParts[0].split("ALTER TABLE ")[1];
            String columnName = queryParts[1].split(" ")[0];
            if (!tableName.matches(".*dx\\d{2}.*")) {
                alteredColumns.add(tableName +"."+columnName);
            }
        }
    }


    public ArrayList<String> getAlteredColumns() {
        return alteredColumns;
    }


}
