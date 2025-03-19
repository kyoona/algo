import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Floyd-Warshall + BFS
 */

public class Juyeon_2610_BFS {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int INF = 100000;

        List<List<Integer>> graph = new ArrayList<>();
        int[][] map = new int[N + 1][N + 1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) map[i][j] = 0;
                else map[i][j] = INF;
            }
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            graph.get(x).add(y);
            graph.get(y).add(x);

            map[x][y] = map[y][x] = 1;
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (i != j) map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

        int[] max = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            int temp = 0;
            for (int j = 1; j <= N; j++) {
                if (i != j && map[i][j] != INF) {
                    temp = Math.max(temp, map[i][j]);
                }
            }
            max[i] = temp;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                List<Integer> committee = new ArrayList<>();
                Queue<Integer> q = new LinkedList<>();
                q.add(i);
                visited[i] = true;

                while (!q.isEmpty()) {
                    int e = q.poll();
                    committee.add(e);

                    for (int v : graph.get(e)) {
                        if (!visited[v]) {
                            q.add(v);
                            visited[v] = true;
                        }
                    }
                }

                int rep = committee.getFirst();

                for (int v : committee) {
                    if (max[rep] > max[v]) rep = v;
                }

                pq.add(rep);
            }
        }

        StringBuilder sb = new StringBuilder();

        sb.append(pq.size()).append("\n");

        while (!pq.isEmpty()) {
            sb.append(pq.poll()).append("\n");
        }

        System.out.println(sb);
    }
}
