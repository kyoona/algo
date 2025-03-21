import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Juyeon_2193 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[] dp = new long[91];

        dp[1] = dp[2] = 1;

        for (int i = 3; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        System.out.println(dp[N]);
    }

//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int N = Integer.parseInt(br.readLine());
//
//        long answer = 1;
//
//        if (N == 1 || N == 2) System.out.println(answer);
//
//        // 3부터 시작
//        if (N >= 3) {
//            long count = 3;
//            long zero = 1;
//            long temp;
//
//            while (count <= N) {
//                temp = answer;
//                answer += zero;
//                zero = temp;
//
//                count++;
//            }
//
//            System.out.println(answer);
//        }
//    }
}
