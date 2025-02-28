import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Juyeon_1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] table = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            table[i][0] = Integer.parseInt(st.nextToken());
            table[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(table, (int[] o1, int[] o2) ->
        {
            if (o1[1] == o2[1]) {
                return o1[0] - o2[0];
            } else return o1[1] - o2[1];
        });

        int count = 0;
        int end = 0;

        for (int i = 0; i < N; i++) {
            if (table[i][0] >= end) {
                count++;
                end = table[i][1];
            }
        }

        System.out.println(count);
    }
}
