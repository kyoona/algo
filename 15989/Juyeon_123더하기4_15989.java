import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Juyeon_123더하기4_15989 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(in.readLine());

        int[] dp = new int[10001];
        dp[0] = 1;

        for (int num = 1; num <= 3; num++) {
            for (int i = num; i <= 10000; i++) {
                dp[i] += dp[i - num];
            }
        }

        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            int n = Integer.parseInt(in.readLine());
            sb.append(dp[n]).append("\n");
        }

        System.out.println(sb);
    }
}
