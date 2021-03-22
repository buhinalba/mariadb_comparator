package jdbc;

import entity.Schema;
import entity.Table;

import java.sql.Connection;
import java.util.ArrayList;

public class MariaDbReader implements DatabaseReader {
    DatabaseManager databaseManager;

    @Override
    public ArrayList<Table> getTables(Schema schema) {
        return null;
    }

    public MariaDbReader() {
        this.databaseManager = new DatabaseManager();   // todo dependency injection
    }
}
