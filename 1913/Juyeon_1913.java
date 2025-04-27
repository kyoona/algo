import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Juyeon_1913 {

    private static final int[] dx = {0, -1, 0, 1};
    private static final int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int target = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N];

        int x = N / 2, y = N / 2;
        int idx = 1;

        int tx = 0, ty = 0;

        int d = 0;

        while (idx <= N * N) {
            map[x][y] = idx;

            if (idx == target) {
                tx = x;
                ty = y;
            }

            idx++;

            int nd = (d + 1) % 4;
            int nx = x + dx[nd];
            int ny = y + dy[nd];

            if (nx >= 0 && ny >= 0 && nx < N && ny < N && map[nx][ny] == 0) {
                d = nd;
                x = nx;
                y = ny;
            } else {
                x += dx[d];
                y += dy[d];
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }

        sb.append(tx + 1).append(" ").append(ty + 1);

        System.out.println(sb);
    }
}
