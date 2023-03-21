package contest2023;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        scanner.nextLine();
        String s = scanner.nextLine();

        Map<Character, Integer> counts = new HashMap<>();
        counts.put('a', 0);
        counts.put('b', 0);
        counts.put('c', 0);
        counts.put('d', 0);

        int left = 0;
        int right = 0;
        int minLength = -1;
        while (right < n) {
            while (right < n) {
                char letter = s.charAt(right);
                counts.put(letter, counts.get(letter) + 1);
                right++;
                if (counts.get('a') >= 1 && counts.get('b') >= 1 && counts.get('c') >= 1 && counts.get('d') >= 1) {
                    break;
                }
            }

            if (right == n && (counts.get('a') < 1 || counts.get('b') < 1 || counts.get('c') < 1 || counts.get('d') < 1)) {
                break;
            }

            if (minLength == -1) {
                minLength = right - left;
            }

            while (left < n && counts.get(s.charAt(left)) > 1) {
                char curLetter = s.charAt(left);
                counts.put(curLetter, counts.get(curLetter) - 1);
                left++;
            }

            if (right - left < minLength && counts.get('a') >= 1 && counts.get('b') >= 1 && counts.get('c') >= 1 && counts.get('d') >= 1) {
                minLength = right - left;
            }

            if (left < n) {
                char curLetter = s.charAt(left);
                counts.put(curLetter, counts.get(curLetter) - 1);
                left++;
            }
        }

        System.out.println(minLength);

        scanner.close();
    }
}
