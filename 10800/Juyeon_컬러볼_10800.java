import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Juyeon_컬러볼_10800 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] balls = new int[N + 1][3];

        for (int idx = 1; idx <= N; idx++) {
            st = new StringTokenizer(br.readLine());

            int color = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());

            balls[idx] = new int[]{idx, color, size};
        }

        Arrays.sort(balls, (o1, o2) -> o1[2] - o2[2]);

        int[] answer = new int[N + 1];

        int[] color = new int[N + 1];
        int total = 0;

        int idx = 1;

        for (int i = 1; i <= N; i++) {
            while (balls[idx][2] < balls[i][2]) {
                total += balls[idx][2];
                color[balls[idx][1]] += balls[idx][2];
                idx++;
            }

            answer[balls[i][0]] = total - color[balls[i][1]];
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            sb.append(answer[i]).append("\n");
        }

        System.out.println(sb);
    }
}
