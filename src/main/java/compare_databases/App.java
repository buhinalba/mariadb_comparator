package compare_databases;

import compare_databases.entity.Column;
import compare_databases.entity.Schema;
import compare_databases.entity.Table;
import compare_databases.jdbc.DatabaseReader;
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
        OutWriter.message("Schemas Imported");
    }


    public App(String schema1, String schema2, DatabaseReader dbReader) {
        this.schemaComparator = new SchemaComparator(new Schema(schema1), new Schema(schema2));
        this.dbReader = dbReader;
    }


    public void printResult() {
        OutWriter.message("\n");
        OutWriter.message("----- DELETED TABLES ("+schemaComparator.getDeletedTables().size()+")");
        OutWriter.printList(schemaComparator.getDeletedTables());

        OutWriter.message("\n");
        OutWriter.message("----- NEW TABLES ("+schemaComparator.getNewTables().size()+")");
        OutWriter.printList(schemaComparator.getNewTables());

        OutWriter.message("\n");
        OutWriter.message("----- DELETED COLUMNS ("+schemaComparator.getDeletedColumns().size()+")");
        OutWriter.printList(schemaComparator.getDeletedColumns());

        OutWriter.message("\n");
        System.out.println("----- NEW COLUMNS ("+schemaComparator.getNewColumns().size()+")");
        OutWriter.printList(schemaComparator.getNewColumns());

        OutWriter.message("\n");
        System.out.println("----- ALTERED COLUMNS ("+schemaComparator.getAlteredColumns().size()+")");
        OutWriter.printHashMap(schemaComparator.getAlteredColumns());
    }

}
