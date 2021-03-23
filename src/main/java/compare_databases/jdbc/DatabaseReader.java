package compare_databases.jdbc;

import compare_databases.entity.Schema;

public interface DatabaseReader {
    void importTables(Schema schema);
}
