package contest2022;

import java.util.*;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        Map<List<String>, Integer> map = new HashMap<>();
        scanner.nextLine();

        for (int i = 0; i < n; i++) {
            List<String> cur = new ArrayList<>(List.of(scanner.nextLine().split(" ")));


            boolean flag = false;
            for (List<String> list : map.keySet()) {
                if (new HashSet<>(list).containsAll(cur)) {
                    map.put(list, map.get(list) + 1);
                    flag = true;
                }
            }

            if (!flag) {
                map.put(cur, 1);
            }
        }

        int max = 0;
        for (Map.Entry<List<String>, Integer> entry : map.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
            }
        }

        System.out.println(max);
    }
}
