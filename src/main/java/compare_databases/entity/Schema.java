package compare_databases.entity;

import java.util.ArrayList;
import java.util.Optional;

public class Schema {
    private final String name;
    private ArrayList<Table> tables;

    public Schema(String name) {
        this.name = name;
        this.tables = new ArrayList<>();
    }

    public Optional<Table> getTable(String name) {
        return tables.stream().filter(table -> table.getName().equals(name)).findFirst();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Table> getTables() {
        return tables;
    }

    public void setTables(ArrayList<Table> tables) {
        this.tables = tables;
    }
}
