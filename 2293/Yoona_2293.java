package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Yoona_2293 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] coin = new int[n];
        long[][] dp = new long[k + 1][n];

        for (int i = 0; i < n; i++) {
            coin[i] = Integer.parseInt(reader.readLine());
        }

        for (int i = 1; i < k + 1; i++) {
            for (int j = 0; j < n; j++) {
                if(j == 0) dp[i][j] = i % coin[j] == 0 ? 1 : 0;
                else {
                    long exceptJCoin = 0;

                    if (i - coin[j] <= 0) exceptJCoin = i % coin[j] == 0 ? 1 : 0;
                    else exceptJCoin = dp[i - coin[j]][j];

                    dp[i][j] = dp[i][j - 1] + exceptJCoin;
                }
            }
        }

        System.out.println(dp[k][n - 1]);
    }
}
