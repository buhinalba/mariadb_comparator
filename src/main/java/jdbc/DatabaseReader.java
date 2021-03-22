package jdbc;

import entity.Schema;
import entity.Table;
import java.util.ArrayList;

public interface DatabaseReader {
    ArrayList<Table> getTables(Schema schema);
}
