package jdbc;

import entity.Table;
import java.util.ArrayList;

public interface DatabaseReader {
    ArrayList<Table> getTables();
}
