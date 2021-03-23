package jdbc;

import entity.Schema;

public interface DatabaseReader {
    void importTables(Schema schema);
}
