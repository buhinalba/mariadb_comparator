package compare_databases.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    public Connection getConnection(String dbName) {
        try {
            String root = System.getenv("MARIADB_USERNAME");
            String pw = System.getenv("MARIADB_PASSWORD");
            // String database = System.getenv("MARIADB_DATABASE_NAME");
            String host = System.getenv("MARIADB_HOST");
            if (root==null || pw==null || dbName ==null || host==null) {
                throw new RuntimeException("Missing Environment variables, can't connect to database!");
            }

            String url = String.format("jdbc:mariadb://%s/%s", host, dbName);
            return DriverManager.getConnection(url, "root", "root");
        } catch (SQLException e) {
            System.out.println("Error while Connecting to database: " + e.getMessage());
            throw new RuntimeException(e);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }



}
