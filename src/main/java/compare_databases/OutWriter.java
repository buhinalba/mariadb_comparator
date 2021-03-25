package compare_databases;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OutWriter {
    public static <E> void printList(List<E> list) {
        for (E item :list) {
            System.out.println(item.toString());
        }
    }

    public static <E> void printHashMap(HashMap<E, E> map) {
        for (Map.Entry<E, E> alteration : map.entrySet()) {
            System.out.printf("%s --> %s%n", alteration.getKey(), alteration.getValue());
        }
    }

    public static void message(String message) {
        System.out.println(message);
    }
}
