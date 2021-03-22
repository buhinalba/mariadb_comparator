import entity.Schema;

public class App {
    private Schema schema1;
    private Schema schema2;


    private boolean importSchema1(String schemaName) {
        return false;
    }

    public boolean importSchema2(String schemaName) {
        return false;
    }

    public void compareSchemas() {

    }

    public void importSchemas(String schemaName1, String schemaName2) {
        if (!importSchema1(schemaName1)) {
            throw new RuntimeException(String.format("Schema %s does not exist!", schemaName1));
        }
        if (!importSchema2(schemaName2)) {
            throw new RuntimeException(String.format("Schema %s does not exist!", schemaName2));
        }
        System.out.println("Schemas Imported"); // todo create view/output class for handling communication with user
    }

}
