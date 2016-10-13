package util.print;

import java.util.List;

/**
 * Created by ubuntu
 */
public class PrintHelper {
    public static void print(List<String[]> list) {
        System.out.print("[");
        for (String[] item : list) {
            System.out.print("(");
            for (String string : item) {
                System.out.print(string + ", ");
            }
            System.out.print(")");
        }
        System.out.println("]");
    }
}
