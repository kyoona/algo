package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Yoona_통계학_2108 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        int[] arr = new int[n];

        int sum = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(reader.readLine());
            min = Integer.min(arr[i], min);
            max = Integer.max(arr[i], max);
            sum += arr[i];
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        List<Integer> ansKey = new ArrayList<>();
        for (Integer key : map.keySet()) {
            if(ansKey.isEmpty() || map.get(key) == map.get(ansKey.get(0))){
                ansKey.add(key);
            }else if (map.get(key) > map.get(ansKey.get(0))){
                ansKey.clear();
                ansKey.add(key);
            }
        }

        Arrays.sort(arr);
        Collections.sort(ansKey);

        System.out.println(Math.round((double) sum / n));
        System.out.println(arr[n/2]);
        System.out.println(ansKey.size() == 1 ? ansKey.get(0) : ansKey.get(1));
        System.out.println(max - min);
    }
}
