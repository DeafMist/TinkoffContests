package contest2022;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Integer> map = new HashMap<>();

        while (scanner.hasNextLine()) {
            String cur = scanner.nextLine();
            if (!Objects.equals(cur, "{") && !Objects.equals(cur, "}")) {
                String[] line = cur.split("=");

                if (!map.containsKey(line[0])) {

                }
            }
        }
    }
}
