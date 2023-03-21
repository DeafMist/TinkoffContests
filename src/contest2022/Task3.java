package contest2022;

import java.util.Arrays;
import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        int[] numbers = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int index = -1;
        int max = 0;

        if (numbers[1] - numbers[0] > max) {
            max = numbers[1] - numbers[0];
            index = 0;
        }

        int i = 1;
        int cur;
        while (i < n - 1) {
            if (i % 2 == 0) {
                cur = numbers[i + 1] - numbers[i];
            }
            else {
                cur = numbers[i] - numbers[i + 1];
            }

            if (cur > max) {
                max = cur;
                index = i;
            }

            i++;
        }

        if (index != -1) {
            int temp = numbers[index];
            numbers[index] = numbers[index + 1];
            numbers[index + 1] = temp;
        }

        int sum = 0;
        for (int j = 0; j < n; j++) {
            if (j % 2 == 0) {
                sum += numbers[j];
            }
            else {
                sum -= numbers[j];
            }
        }

        System.out.println(sum);
    }
}
