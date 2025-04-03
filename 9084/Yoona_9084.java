package org.example;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Yoona_9084 {
    public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(reader.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(reader.readLine());
            int[] coins = new int[n];

            st = new StringTokenizer(reader.readLine());
            for (int j = 0; j < n; j++) {
                coins[j] = Integer.parseInt(st.nextToken());
            }

            int m = Integer.parseInt(reader.readLine());
            int[][] dp = new int[m + 1][n];
            calDp(n, m, coins, dp);
            writer.write(dp[m][n - 1] + "\n");
        }

        writer.flush();
    }

    public static void calDp(int n, int m, int[] coins, int[][] dp){
        Arrays.fill(dp[0], 1);
        for (int i = 1; i < m + 1; i++) {
            for (int j = 0; j < n; j++) {
                int prevCoinDp = i - coins[j] >= 0 ? dp[i-coins[j]][j] : 0;
                if (j == 0) {
                    dp[i][j] = i % coins[j] == 0 ? 1 : 0;
                } else {
                    dp[i][j] = prevCoinDp + dp[i][j - 1];
                }
            }
        }
    }
}
