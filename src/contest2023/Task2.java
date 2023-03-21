package contest2023;

import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int k = scanner.nextInt();
            System.out.println((n * k + m - 1) / m);
        }
    }
}
