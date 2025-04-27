import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Juyeon_16236 {

    private static int N;
    private static int[][] map;
    private static int[] shark = {2, 0};
    private static int sec = 0;

    private static int[] dx = {-1, 0, 0, 1};
    private static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        // 상어 출발 위치
        int x = 0, y = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    x = i;
                    y = j;
                    map[i][j] = 0;
                }
            }
        }

        while (true) {
            int[] e = BFS(x, y);
            if (e == null) break;

            int nx = e[0];
            int ny = e[1];
            int dist = e[2];

            sec += dist;
            shark[1]++;
            if (shark[0] == shark[1]) {
                shark[0]++;
                shark[1] = 0;
            }

            map[nx][ny] = 0;
            x = nx;
            y = ny;
        }

        System.out.println(sec);
    }

    private static int[] BFS(int x, int y) {
        Queue<int[]> q = new LinkedList<>();

        boolean[][] visited = new boolean[N][N];

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[2] != o2[2]) return o1[2] - o2[2];
            if (o1[0] != o2[0]) return o1[0] - o2[0];
            return o1[1] - o2[1];
        });

        q.add(new int[]{x, y, 0});
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] e = q.poll();

            x = e[0];
            y = e[1];
            int dist = e[2];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (canGo(nx, ny) && !visited[nx][ny] && map[nx][ny] <= shark[0]) {
                    visited[nx][ny] = true;

                    if (map[nx][ny] < shark[0] && map[nx][ny] != 0) {
                        pq.add(new int[]{nx, ny, dist + 1});
                    }

                    q.add(new int[]{nx, ny, dist + 1});
                }
            }
        }

        return pq.isEmpty() ? null : pq.poll();
    }

    private static boolean canGo(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}
