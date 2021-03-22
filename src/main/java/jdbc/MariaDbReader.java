package jdbc;

import entity.Column;
import entity.Schema;
import entity.Table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MariaDbReader implements DatabaseReader {
    DatabaseManager databaseManager;


    @Override
    public ArrayList<Table> getTables(Schema schema) {
        // todo select all tables
        // todo go through tables in Schema -> select all columns (name, type)
        try {
            Connection conn = databaseManager.getConnection();
            String query = "SELECT table_name FROM information_schema.tables WHERE table_type = 'base table' AND table_schema=?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, schema.getName());

            ResultSet rs = preparedStatement.executeQuery();

            ArrayList<Table> tables = new ArrayList<>();
            while (rs.next()) {
                System.out.println(rs.getString(1));
                tables.add(new Table(rs.getString(1)));
            }
            return tables;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public MariaDbReader() {
        this.databaseManager = new DatabaseManager();   // todo dependency injection
    }

    private ArrayList<Column> getColumns(Table table) {
        return null;
    }
}
