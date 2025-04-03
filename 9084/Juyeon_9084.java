import java.io.*;
import java.util.StringTokenizer;

public class Juyeon_9084 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());

            int[] coins = new int[N];

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }

            int M = Integer.parseInt(br.readLine());

            int[] dp = new int[M + 1];
            dp[0] = 1;

            for (int coin : coins) {
                for (int j = coin; j <= M; j++) {
                    dp[j] += dp[j - coin];
                }
            }

            bw.write(dp[M] + "\n");
        }

        bw.flush();
        bw.close();
    }
}
