import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Floyd-Warshall + Union-Find
 */

public class Juyeon_2610_UnionFind {

    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int INF = 100000;

        int[][] map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) Arrays.fill(map[i], INF);
        for (int i = 1; i <= N; i++) map[i][i] = 0;

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) parent[i] = i;

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            union(x, y);

            map[x][y] = map[y][x] = 1;
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (i != j && map[i][k] != INF && map[k][j] != INF) {
                        map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                    }
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

        Map<Integer, List<Integer>> committees = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            int root = find(i);
            committees.computeIfAbsent(root, k -> new ArrayList<>()).add(i);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (List<Integer> list : committees.values()) {
            int rep = list.getFirst();

            for (int i : list) {
                if (max[rep] > max[i]) {
                    rep = i;
                }
            }

            pq.add(rep);
        }

        StringBuilder sb = new StringBuilder();

        sb.append(pq.size()).append("\n");

        while (!pq.isEmpty()) {
            sb.append(pq.poll()).append("\n");
        }

        System.out.println(sb);
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) parent[y] = x;
    }

    private static int find(int x) {
        if (x == parent[x]) return x;
        else return parent[x] = find(parent[x]);
    }
}
