package org.example;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Yoona_13398 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        StringTokenizer st = new StringTokenizer(scanner.nextLine());

        long[][] dp = new long[n][2];
        dp[0][1] = dp[0][0] = Integer.parseInt(st.nextToken());

        long ans = dp[0][0];
        for (int i = 1; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            dp[i][0] = Long.max(dp[i - 1][0] + num, num);
            dp[i][1] = Long.max(dp[i - 1][1] + num, dp[i - 1][0]);
            ans = Long.max(Long.max(dp[i][0], dp[i][1]), ans);
        }

        System.out.println(ans);
    }
}
