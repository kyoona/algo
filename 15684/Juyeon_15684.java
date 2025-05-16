import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Juyeon_15684 {

    private static int N, H, answer;
    private static int[][] map;
    private static boolean done = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map[a][b] = 1;
            map[a][b + 1] = -1;
        }

        for (int i = 0; i <= 3; i++) {
            answer = i;
            DFS(1, 0);
            if (done) break;
        }

        System.out.println(done ? answer : -1);
    }

    private static void DFS(int x, int count) {
        if (done) return;

        if (answer == count) {
            if (isValid()) done = true;
            return;
        }

        for (int i = x; i <= H; i++) {
            for (int j = 1; j < N; j++) {
                if (map[i][j] == 0 && map[i][j + 1] == 0) {
                    map[i][j] = 1;
                    map[i][j + 1] = -1;
                    DFS(i, count + 1);
                    map[i][j] = map[i][j + 1] = 0;
                }
            }
        }
    }

    private static boolean isValid() {
        for (int i = 1; i <= N; i++) {
            int x = 1, y = i;
            for (int j = 1; j <= H; j++) {
                if (map[x][y] == 1) y++;
                else if (map[x][y] == -1) y--;
                x++;
            }

            if (y != i) return false;
        }

        return true;
    }
}
