import entity.Column;
import entity.Schema;
import entity.Table;
import jdbc.DatabaseReader;

import java.util.List;
import java.util.Map;

public class App {
    private final Schema schema1;
    private final Schema schema2;
    DatabaseReader dbReader;

    List<Table> deletedTables;
    List<Table> newTables;
    List<Table> commonTables;
    List<Column> deletedColumns;
    List<Column> newColumns;
    List<Map.Entry<Column, Column>> alteredColumns;

    public void compareSchemas() {
        // thought: factor methods out to SchemaComparator class ?
        checkForDeletedTables();
        checkForNewTables();
        checkForDeletedColumns();
        checkForNewColumns();
        checkForDeletedColumns();
    }


    public void importSchemas() {
        if (!dbReader.importTables(schema1)) {
            throw new RuntimeException(String.format("Schema %s does not exist!", schema1.getName()));
        }
        if (!dbReader.importTables(schema2)) {
            throw new RuntimeException(String.format("Schema %s does not exist!", schema2.getName()));
        }
        System.out.println("Schemas Imported"); // todo create view/output class for handling communication with user
    }


    public App(String schema1, String schema2, DatabaseReader dbReader) {
        this.schema1 = new Schema(schema1);
        this.schema2 = new Schema(schema2);
        this.dbReader = dbReader;
    }


    private void printResult() {
        // todo format and print differences
    }


    private void checkForDeletedTables() {
        for (Table table: schema1.getTables()) {
            if (schema2.getTable(table.getName()).isEmpty()) {
                deletedTables.add(table);
            } else {
                commonTables.add(table);
            }
        }
    }


    private void checkForNewTables() {
        // todo go through tables of schema2 - check if in schema1
    }


    private void checkForDeletedColumns() {
        // todo go through commonTables
    }


    private void checkForNewColumns() {
        // todo go through commonTables
    }


    private void checkForAlteredColumns() {
        // todo go through commonTables
    }
}
