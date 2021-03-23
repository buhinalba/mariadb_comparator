package compare_databases;

import compare_databases.entity.Column;
import compare_databases.entity.Schema;
import compare_databases.entity.Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class SchemaComparator {
    private Schema schema1;
    private Schema schema2;

    private List<Table> deletedTables;
    private List<Table> newTables;
    private List<Column> deletedColumns = new ArrayList<>();
    private List<Column> newColumns = new ArrayList<>();
    private HashMap<Column, Column> alteredColumns = new HashMap<>();

    public SchemaComparator(Schema schema1, Schema schema2) {
        this.schema1 = schema1;
        this.schema2 = schema2;
    }

    public void compareSchemas() {
        checkForDeletedOrAlteredTables();
        checkForNewTables();
    }


    private void checkForDeletedOrAlteredTables() {
        deletedTables = new ArrayList<>();

        for (Table table: schema1.getTables()) {
            Optional<Table> schema2Table = schema2.getTable(table.getName());
            if (schema2Table.isEmpty()) {
                deletedTables.add(table);
            } else {
                checkForDeletedColumns(table, schema2Table.get());
                checkForNewColumns(table, schema2Table.get());
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
        for (Column column: oldTable.getColumns()) {
            Optional<Column> newTableColumn = newTable.getColumn(column.getName());
            if (newTableColumn.isEmpty()) {
                deletedColumns.add(column);
            } else {
                checkForColumnAlteration(column, newTableColumn.get());
            }
        }
    }


    private void checkForNewColumns(Table oldTable, Table newTable) {
        newColumns = new ArrayList<>();
        for (Column column: newTable.getColumns()) {
            Optional<Column> newTableColumn = oldTable.getColumn(column.getName());
            if (newTableColumn.isEmpty()) {
                newColumns.add(column);
            }
        }
    }


    private void checkForColumnAlteration(Column oldColumn, Column newColumn) {
        if (!oldColumn.getType().equals(newColumn.getType())) {
            alteredColumns.put(oldColumn, newColumn);
        }
    }

    public Schema getSchema1() {
        return schema1;
    }

    public Schema getSchema2() {
        return schema2;
    }

    public List<Table> getDeletedTables() {
        return deletedTables;
    }

    public List<Table> getNewTables() {
        return newTables;
    }

    public List<Column> getDeletedColumns() {
        return deletedColumns;
    }

    public List<Column> getNewColumns() {
        return newColumns;
    }

    public HashMap<Column, Column> getAlteredColumns() {
        return alteredColumns;
    }
}
