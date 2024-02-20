package datascienseschool;

import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        int a, b, c, d;
        Scanner scanner = new Scanner(System.in);
        a = scanner.nextInt();
        b = scanner.nextInt();
        c = scanner.nextInt();
        d = scanner.nextInt();

        if (a == 0 && b == 0) {
            System.out.println("INF");
        } else if (a == 0) {
            System.out.println("NO");
        } else {
            double x = (double) -b / a;
            double res = c * x + d;
            if (res == 0) {
                System.out.println("NO");
            } else {
                System.out.println(x);
            }
        }

        scanner.close();
    }
}
