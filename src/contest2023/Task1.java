package contest2023;

import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = 4;

        boolean nonDecreasing = true;
        boolean nonIncreasing = true;
        int prev = scanner.nextInt();
        for (int i = 1; i < n; i++) {
            int cur = scanner.nextInt();
            if (prev < cur) {
                nonDecreasing = false;
            }

            if (prev > cur) {
                nonIncreasing = false;
            }

            prev = cur;
        }

        if (nonDecreasing || nonIncreasing) {
            System.out.println("YES");
        }
        else {
            System.out.println("NO");
        }

        scanner.close();
    }
}
