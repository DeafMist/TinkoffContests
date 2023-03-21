package contest2023;

import java.util.Arrays;
import java.util.Scanner;

public class Task6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int s = scanner.nextInt();

        int[] l = new int[n];
        int[] r = new int[n];

        int left = 0;
        int right = 0;

        for (int i = 0; i < n; i++) {
            l[i] = scanner.nextInt();
            r[i] = scanner.nextInt();

            left += l[i];
            right += r[i];
        }

        s = Math.min(right, s);

        right = s - left;
        left = 0;
        int maxMedian = 0;
        while (left < right) {
            int mid = (left + right + 1) / 2;

            int[] marks = new int[n];
            int sum = 0;
            for (int i = 0; i < n; i++) {
                if (l[i] > mid) {
                    marks[i] = l[i];
                }
                else {
                    marks[i] = Math.min(r[i], mid);
                }
                sum += marks[i];
            }

            Arrays.sort(marks);
            int median = marks[(n + 1) / 2];

            if (median > maxMedian) {
                maxMedian = median;
            }

            if (sum < s) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(maxMedian);

        scanner.close();
    }
}
