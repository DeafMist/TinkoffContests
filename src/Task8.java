import java.util.*;

public class Task8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.nextLine();

        Set<String> set = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            set.add(scanner.nextLine());
        }

        for (int i = 0; i < m; i++) {
            String[] line = scanner.nextLine().split(" ");
            String prefix = line[0];
            String postfix = line[1];

            int count = 0;
            for (String elem : set) {
                if (elem.indexOf(prefix) == 0 && elem.indexOf(postfix) == elem.length() - postfix.length()) {
                    count++;
                }
            }

            System.out.println(count);
        }
    }
}
