package org.example;

import java.util.Scanner;

public class Yoona_10844 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long mod = 1000000000;
        int n = scanner.nextInt();

        long[][] dp = new long[n][10];
        dp[0][0] = 0;
        for (int i = 1; i < 10; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 10; j++) {
                if(j == 0) dp[i][0] = dp[i - 1][1] % mod;
                else if (j == 9) dp[i][9] = dp[i - 1][8] % mod;
                else dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % mod;
            }
        }

        long ans = 0;
        for (int i = 0; i < 10; i++) {
            ans = (ans + dp[n - 1][i]) % mod;
        }
        System.out.println(ans);
    }
}
