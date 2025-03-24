package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Yoona_1940 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int m = Integer.parseInt(reader.readLine());

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int[] items = new int[n];
        for (int i = 0; i < n; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(items);
        int start = 0;
        int end = items.length - 1;
        int ans = 0;
        while (start < end) {
            int sum = items[start] + items[end];
            if(sum < m) start++;
            else if(sum > m) end--;
            else{
                start++;
                end--;
                ans++;
            }
        }

        System.out.println(ans);
    }
}
