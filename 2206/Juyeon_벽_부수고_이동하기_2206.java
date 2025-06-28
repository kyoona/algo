import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Juyeon_벽_부수고_이동하기_2206 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            String line = br.readLine();
            for (int j = 1; j <= M; j++) {
                map[i][j] = line.charAt(j - 1) - '0';
            }
        }

        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{1, 1, 1, 0}); // x, y, dist, 벽

        boolean[][][] visited = new boolean[N + 1][M + 1][2];

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int x = curr[0], y = curr[1], dist = curr[2], broke = curr[3];

            if (x == N && y == M) {
                System.out.println(dist);
                return;
            }

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 1 || ny < 1 || N < nx || M < ny) continue;

                if (map[nx][ny] == 0 && !visited[nx][ny][broke]) {
                    visited[nx][ny][broke] = true;
                    q.add(new int[]{nx, ny, dist + 1, broke});
                }

                if (map[nx][ny] == 1 && broke == 0 && !visited[nx][ny][1]) {
                    visited[nx][ny][1] = true;
                    q.add(new int[]{nx, ny, dist + 1, 1});
                }
            }
        }

        System.out.println(-1);
    }
}
