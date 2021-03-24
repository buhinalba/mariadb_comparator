package count_column_alterations;

public class Main {

    public static void main(String[] args) throws Exception {

        DirectoryTraverser directoryTraverser = new DirectoryTraverser("/home/buhinalba/Projects/Data_engineer_assignment/src");
        directoryTraverser.traverse();
        directoryTraverser.printResult();
    }
}
