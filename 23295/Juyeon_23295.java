import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Juyeon_23295 {

    private static final int MAX = 100001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        int[] timeline = new int[MAX];

        for (int i = 0; i < N; i++) {
            int K = Integer.parseInt(br.readLine());

            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine());

                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());

                timeline[s]++;
                timeline[e]--;
            }
        }

        for (int i = 1; i < MAX; i++) {
            timeline[i] += timeline[i - 1];
        }

        int sum = 0;
        for (int i = 0; i < T; i++) {
            sum += timeline[i];
        }

        int max = sum;
        int l = 0, s = 0;

        for (int r = T; r < MAX; ++r) {
            sum -= timeline[l++];
            sum += timeline[r];

            if (sum > max) {
                max = sum;
                s = l;
            }
        }

        System.out.println(s + " " + (s + T));
    }
}
