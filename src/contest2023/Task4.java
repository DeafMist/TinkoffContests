package contest2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Task4 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(br.readLine());
            String[] arr = br.readLine().split(" ");

            Map<Integer, Integer> map = new HashMap<>();
            Map<Integer, Set<Integer>> countsAndNumbers = new TreeMap<>();
            int max = 1;
            int min = Integer.MIN_VALUE;
            int countOfMaxes = 1;
            map.put(Integer.parseInt(arr[0]), max);

            Set<Integer> set = countsAndNumbers.getOrDefault(max, new HashSet<>());
            set.add(Integer.parseInt(arr[0]));
            countsAndNumbers.put(max, set);

            int maxL = Integer.MIN_VALUE;
            for (int i = 1; i < n; i++) {
                int number = Integer.parseInt(arr[i]);
                int count = map.getOrDefault(number, 0) + 1;
                map.put(number, count);

                if (count > max) {
                    max = count;
                    countOfMaxes = 1;
                }
                else if (count == max) {
                    countOfMaxes++;
                }

                set = countsAndNumbers.getOrDefault(count, new HashSet<>());
                set.add(number);
                countsAndNumbers.put(count, set);

                if (countsAndNumbers.containsKey(count - 1)) {
                    if (countsAndNumbers.get(count - 1).size() == 1  && count - 1 == min) {
                        countsAndNumbers.remove(count - 1);
                    }
                    else {
                        countsAndNumbers.get(count - 1).remove(number);
                    }
                }

                min = (int) countsAndNumbers.keySet().toArray()[0];

                if (countOfMaxes == 1 && max - min == 1 || min == max && countOfMaxes == i + 1 || countsAndNumbers.get(min).size() == 1 && countOfMaxes == map.keySet().size() - 1) {
                    maxL = i + 1;
                }
            }

            System.out.println(maxL);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
