import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Juyeon_7453 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[][] ABCD = new int[n][4];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 4; j++) {
                ABCD[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] AB = new int[n * n];
        int[] CD = new int[n * n];

        int idx = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                AB[idx] = ABCD[i][0] + ABCD[j][1];
                CD[idx] = ABCD[i][2] + ABCD[j][3];

                idx++;
            }
        }

        Arrays.sort(AB);
        Arrays.sort(CD);

        // binary search
        int l = 0;
        int r = AB.length - 1;

        long count = 0;

        while (l < AB.length && 0 <= r) {
            int sum = AB[l] + CD[r];

            if (sum == 0) {
                int v = AB[l];
                long ab = 0;

                while (l < AB.length && AB[l] == v) {
                    ab++;
                    l++;
                }

                v = CD[r];
                long cd = 0;

                while (0 <= r && CD[r] == v) {
                    cd++;
                    r--;
                }

                count += ab * cd;

            } else if (sum < 0) {
                l++;
            } else {
                r--;
            }
        }

        System.out.println(count);
    }
}
