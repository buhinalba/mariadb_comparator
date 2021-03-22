import entity.Column;
import entity.Schema;
import entity.Table;
import jdbc.DatabaseReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class App {
    private final Schema schema1;
    private final Schema schema2;
    DatabaseReader dbReader;

    private List<Table> deletedTables;
    private List<Table> newTables;
    private List<Table> commonTables;
    private List<Column> deletedColumns;
    private List<Column> newColumns;
    private List<Map.Entry<Column, Column>> alteredColumns;

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
        deletedTables = new ArrayList<>();
        commonTables = new ArrayList<>();

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
