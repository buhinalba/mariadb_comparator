import entity.Schema;
import jdbc.DatabaseReader;

public class App {
    private Schema schema1;
    private Schema schema2;
    DatabaseReader dbReader;


    public void compareSchemas() {
        /* CHECK FOR:
            - deleted tables
            - new tables

            - deleted columns
            - new columns
            - altered columns (type changed)
         */

    }

    public void importSchemas() {
        if (!dbReader.importTables(schema1)) {
            throw new RuntimeException(String.format("Schema %s does not exist!", schema1.getName()));
        }
        if (!dbReader.importTables(schema2)) {
            throw new RuntimeException(String.format("Schema %s does not exist!", schema2.getName()));
        }
        System.out.println("Schemas Imported"); // todo create view/output class for handling communication with user
    }

    public App(String schema1, String schema2, DatabaseReader dbReader) {
        this.schema1 = new Schema(schema1);
        this.schema2 = new Schema(schema2);
        this.dbReader = dbReader;
    }
}
