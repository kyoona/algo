import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Juyeon_2178 {

    static int[][] map;
    static boolean[][] visited;
    static int N;
    static int M;

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            String line = br.readLine();

            for (int j = 1; j <= M; j++) {
                map[i][j] = line.charAt(j - 1) - '0';
            }
        }

        visited = new boolean[N + 1][M + 1];

        System.out.println(BFS());
    }

    private static int BFS() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{1, 1, 1}); // x, y, d
        visited[1][1] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int d = cur[2];

            if (x == N && y == M) return d;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (isValid(nx, ny)) {
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny, d + 1});
                }
            }
        }

        return -1;
    }

    private static boolean isValid(int x, int y) {
        return x > 0 && y > 0 && x <= N && y <= M && map[x][y] == 1 && !visited[x][y];
    }
}

/*
4 6
101111
101010
101011
111011

15
 */

/*
4 6
110110
110110
111111
111101

9
 */

/*
2 25
1011101110111011101110111
1110111011101110111011101

38
 */
