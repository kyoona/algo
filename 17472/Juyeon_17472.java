import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Juyeon_17472 {

    private static int N, M;
    private static int[][] map;
    private static boolean[][] visited;

    private static final int[] dx = new int[]{0, 0, -1, 1};
    private static final int[] dy = new int[]{1, -1, 0, 0};

    private static PriorityQueue<int[]> pq;
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][M];

        // indexing 때문에 2, count 는 -2 해야죵
        int island = 2;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    indexing(i, j, island++);
                }
            }
        }

        pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0) buildBridge(i, j, map[i][j]);
            }
        }

        parent = new int[island];
        for (int i = 1; i < island; i++) parent[i] = i;

        int len = 0;
        int edgeCount = 0;

        while (!pq.isEmpty()) {
            int[] e = pq.poll();

            int from = e[0];
            int to = e[1];
            int cost = e[2];

            if (find(from) != find(to)) {
                len += cost;
                union(from, to);
                edgeCount++;
            }
        }

        // 간선 개수 = 정점 개수 - 1이면 모든 섬이 연결되어 있는 거고, 섬의 개수는 island - 2( indexing 때문에 2로 시작했으니까)
        System.out.println(edgeCount == island - 3 ? len : -1);
    }

    private static void indexing(int i, int j, int idx) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});
        map[i][j] = idx;
        visited[i][j] = true;

        while (!q.isEmpty()) {
            int[] e = q.poll();

            int x = e[0];
            int y = e[1];

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (cantGo(nx, ny) || visited[nx][ny] || map[nx][ny] != 1) continue;

                map[nx][ny] = idx;
                visited[nx][ny] = true;
                q.add(new int[]{nx, ny});
            }
        }
    }

    private static boolean cantGo(int x, int y) {
        return x < 0 || y < 0 || N <= x || M <= y;
    }

    private static void buildBridge(int x, int y, int idx) {
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            int len = 0;

            while (true) {
                if (cantGo(nx, ny) || map[nx][ny] == idx) break;

                if (map[nx][ny] != 0) {
                    if (len >= 2) pq.add(new int[]{idx, map[nx][ny], len});
                    break;
                } else {
                    nx += dx[d];
                    ny += dy[d];
                    len++;
                }
            }
        }
    }

    private static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) parent[x] = y;
    }
}
