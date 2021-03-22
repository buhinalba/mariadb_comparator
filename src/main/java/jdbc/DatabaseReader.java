package jdbc;

import entity.Schema;

public interface DatabaseReader {
    boolean importTables(Schema schema);
}
