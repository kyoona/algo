package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Yoona_11047 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = reader.readLine().split(" ");

        int n = Integer.parseInt(firstLine[0]);
        int k = Integer.parseInt(firstLine[1]);

        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(reader.readLine());
        }

        int ans = 0;
        for (int i = n - 1; i >= 0 ; i--) {
            if(k >= coins[i] && k >= 0){
                int count = k / coins[i];
                k -= count * coins[i];
                ans += count;
            }
        }

        System.out.print(ans);
    }

}
