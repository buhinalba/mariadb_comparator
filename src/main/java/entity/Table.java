package entity;

import java.util.ArrayList;
import java.util.Optional;

public class Table {
    private String name;
    private ArrayList<Column> columns;
    String schemaName;

    public Table(String name, String schemaName) {
        this.name = name;
        this.columns = new ArrayList<>();
        this.schemaName = schemaName;
    }

    public ArrayList<Column> getColumns() {
        return columns;
    }

    public void addColumn(Column column) {
        columns.add(column);
    }

    public String getName() {
        return name;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public Optional<Column> getColumn(String name) {
        return columns.stream().filter(column -> column.getName().equals(name)).findFirst();
    }
}
