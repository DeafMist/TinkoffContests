import java.util.*;

public class Task5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int q = scanner.nextInt();
        scanner.nextLine();

        Map<String, Integer> map = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            map.put(scanner.nextLine(), i + 1);
        }

        for (int i = 0; i < q; i++) {
            String[] line = scanner.nextLine().split(" ");
            String prefix = line[1];
            int k = Integer.parseInt(line[0]);

            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getKey().indexOf(prefix) == 0) {
                    if (k == 1) {
                        System.out.println(entry.getValue());
                        break;
                    }

                    k--;
                }
            }
        }
    }
}
