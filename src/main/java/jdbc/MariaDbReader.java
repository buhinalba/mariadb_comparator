package jdbc;

import entity.Column;
import entity.Schema;
import entity.Table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MariaDbReader implements DatabaseReader {
    DatabaseManager databaseManager;


    @Override
    public ArrayList<Table> importTables(Schema schema) {
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
            conn.close();
            for (Table table: tables) {
                importColumns(table);
            }
            schema.setTables(tables);

            return tables; // todo question -> is this necessary?? a boolean return could indicate if the import worked successfully or not
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public MariaDbReader() {
        this.databaseManager = new DatabaseManager();   // todo dependency injection
    }

    private ArrayList<Column> importColumns(Table table) throws SQLException {
        Connection conn = databaseManager.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("SHOW COLUMNS FROM " + table.getName());
            ResultSet rs = preparedStatement.executeQuery();

            ArrayList<Column> columns = new ArrayList<>();
            while (rs.next()) {
                String columnName = rs.getString(1);
                String columnType = rs.getString(2);

                Column column = new Column(columnName, columnType);
                columns.add(column);
                table.addColumn(column);
                System.out.println(columnName + " - " + columnType);
            }
            conn.close();
            return columns;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            conn.close();
            throw new RuntimeException(e);
        }
    }
}
