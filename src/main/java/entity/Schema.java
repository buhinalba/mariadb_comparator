package entity;

import java.util.ArrayList;

public class Schema {
    private String name;
    private ArrayList<Table> tables;

    public Schema(String name) {
        this.name = name;
        this.tables = new ArrayList<>();
    }

    public Table getTable(String name) {
        return null;
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
