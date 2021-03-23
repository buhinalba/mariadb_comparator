package compare_databases;

import compare_databases.jdbc.DatabaseManager;
import compare_databases.jdbc.MariaDbReader;

public class Main {
    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();
        App app = new App("dwh", "dwh_new", new MariaDbReader(dbManager));
        app.importSchemas();
        app.compareSchemas();
        app.printResult();
    }
}

