package compare_databases.entity;

public class Column {
    private String tableName;
    private String name;
    private String type;

    public Column(String name, String type, String tableName) {
        this.name = name;
        this.type = type;
        this.tableName = tableName;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getTableName() {
        return tableName;
    }


    @Override
    public String toString() {
        return String.format("%s.%s %s", tableName, name, type);
    }
}
