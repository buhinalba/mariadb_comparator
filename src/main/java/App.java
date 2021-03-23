import entity.Column;
import entity.Schema;
import entity.Table;
import jdbc.DatabaseReader;
import java.util.*;


public class App {
    private final SchemaComparator schemaComparator;
    DatabaseReader dbReader;


    public void compareSchemas() {
        schemaComparator.compareSchemas();
    }


    public void importSchemas() {
        dbReader.importTables(schemaComparator.getSchema1());
        dbReader.importTables(schemaComparator.getSchema2());
        System.out.println("Schemas Imported"); // todo create view/output class for handling communication with user
    }


    public App(String schema1, String schema2, DatabaseReader dbReader) {
        this.schemaComparator = new SchemaComparator(new Schema(schema1), new Schema(schema2));
        this.dbReader = dbReader;
    }


    public void printResult() {
        System.out.println();
        System.out.println("----- DELETED TABLES ("+schemaComparator.getDeletedTables().size()+")");
        for (Table table: schemaComparator.getDeletedTables()) {
            System.out.println(table.getName());
        }

        System.out.println();
        System.out.println("----- NEW TABLES ("+schemaComparator.getNewTables().size()+")");
        for (Table table: schemaComparator.getNewTables()) {
            System.out.println(table.getName());
        }

        System.out.println();
        System.out.println("----- DELETED COLUMNS ("+schemaComparator.getDeletedColumns().size()+")");
        for (Column column: schemaComparator.getDeletedColumns()) {
            System.out.println(column.getTableName()+"."+column.getName());
        }

        System.out.println();
        System.out.println("----- NEW COLUMNS ("+schemaComparator.getNewColumns().size()+")");
        for (Column column: schemaComparator.getNewColumns()) {
            System.out.println(column.getTableName()+"."+column.getName());
        }

        System.out.println();
        System.out.println("----- ALTERED COLUMNS ("+schemaComparator.getAlteredColumns().size()+")");
        for (Map.Entry<Column, Column> alteration : schemaComparator.getAlteredColumns().entrySet()) {
            System.out.printf("%s --> %s%n", alteration.getKey().toString(), alteration.getValue().toString());
        }


    }

}
