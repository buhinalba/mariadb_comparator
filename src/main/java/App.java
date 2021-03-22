import entity.Column;
import entity.Schema;
import entity.Table;
import jdbc.DatabaseReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        checkForDeletedOrAlteredTables();
        checkForNewTables();
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


    private void checkForDeletedOrAlteredTables() {
        deletedTables = new ArrayList<>();
        commonTables = new ArrayList<>();

        for (Table table: schema1.getTables()) {
            Optional<Table> schema2Table = schema2.getTable(table.getName());
            if (schema2Table.isEmpty()) {
                deletedTables.add(table);
            } else {
                checkForDeletedColumns(table, schema2Table.get());
                checkForNewColumns(table, schema2Table.get());
                checkForAlteredColumns(table, schema2Table.get());
                commonTables.add(table);
            }
        }
    }


    private void checkForNewTables() {
        newTables = new ArrayList<>();
        for (Table table: schema2.getTables()) {
            if (schema1.getTable(table.getName()).isEmpty()) {
                newTables.add(table);
            }
        }
    }


    private void checkForDeletedColumns(Table oldTable, Table newTable) {
        deletedColumns = new ArrayList<>();
    }


    private void checkForNewColumns(Table oldTable, Table newTable) {
        // todo go through commonTables
    }


    private void checkForAlteredColumns(Table oldTable, Table newTable) {
        // todo go through commonTables
    }
}
