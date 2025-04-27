import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Juyeon_1738 {

    private static int n;
    private static List<List<int[]>> graph;
    private static int[] dist;
    private static int[] prev;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

        dist = new int[n + 1];
        Arrays.fill(dist, Integer.MIN_VALUE);
        dist[1] = 0;

        prev = new int[n + 1];
        visited = new boolean[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new int[]{v, w});
        }

        if (findCycle() && visited[n]) System.out.println(-1);
        else printPath();
    }

    private static boolean findCycle() {
        boolean cycle = false;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int[] edge : graph.get(j)) {
                    int to = edge[0];
                    int weight = edge[1];

                    if (dist[j] != Integer.MIN_VALUE && dist[j] + weight > dist[to]) {
                        dist[to] = dist[j] + weight;
                        prev[to] = j;

                        if (i == n) {
                            cycle = true;
                            DFS(to);
                        }
                    }
                }
            }
        }

        return cycle;
    }

    private static void DFS(int node) {
        visited[node] = true;

        for (int[] edge : graph.get(node)) {
            int next = edge[0];

            if (!visited[next]) DFS(next);
        }
    }

    private static void printPath() {
        List<Integer> path = new ArrayList<>();

        int curr = n;

        while (curr != 0) {
            path.add(curr);
            if (curr == 1) break;
            curr = prev[curr];
        }

        Collections.reverse(path);

        StringBuilder sb = new StringBuilder();

        for (int i : path) {
            sb.append(i).append(" ");
        }

        System.out.println(sb);
    }
}
