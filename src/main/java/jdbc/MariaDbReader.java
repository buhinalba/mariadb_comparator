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

    public MariaDbReader(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @Override
    public boolean importTables(Schema schema) {
        try {
            Connection conn = databaseManager.getConnection(schema.getName());
            String query = "SELECT table_name FROM information_schema.tables WHERE table_type = 'base table' AND table_schema=?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, schema.getName());

            ResultSet rs = preparedStatement.executeQuery();

            ArrayList<Table> tables = new ArrayList<>();
            while (rs.next()) {
                System.out.println(rs.getString(1));
                tables.add(new Table(rs.getString(1), schema.getName()));
            }
            conn.close();
            for (Table table: tables) {
                importColumns(table);
            }
            schema.setTables(tables);

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    private void importColumns(Table table) throws SQLException {
        Connection conn = databaseManager.getConnection(table.getSchemaName());
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("SHOW COLUMNS FROM " + table.getName());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String columnName = rs.getString(1);
                String columnType = rs.getString(2);

                Column column = new Column(columnName, columnType);
                table.addColumn(column);
                System.out.println(columnName + " - " + columnType);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            conn.close();
        }
    }


}
