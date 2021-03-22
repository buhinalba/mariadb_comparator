package jdbc;

import entity.Table;

import java.util.ArrayList;

public class DatabaseReaderMariadb implements DatabaseReader {
    DatabaseManager databaseManager;

    @Override
    public ArrayList<Table> getTables() {
        return null;
    }

    public DatabaseReaderMariadb(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }
}
