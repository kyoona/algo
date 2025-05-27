import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Juyeon_14938 {

    private static int n, m;
    private static int[] item;
    private static List<List<int[]>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        item = new int[n + 1];

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            item[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new int[]{v, w});
            graph.get(v).add(new int[]{u, w});
        }

        int max = 0;
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, dijkstra(i));
        }

        System.out.println(max);
    }

    private static int dijkstra(int s) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.add(new int[]{s, 0});

        while (!pq.isEmpty()) {
            int[] e = pq.poll();
            int curr = e[0];
            int cost = e[1];

            if (cost > dist[curr]) continue;

            for (int[] next : graph.get(curr)) {
                int weight = cost + next[1];

                if (weight < dist[next[0]] && weight <= m) {
                    dist[next[0]] = weight;
                    pq.add(new int[]{next[0], weight});
                }
            }
        }

        int total = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] <= m) total += item[i];
        }

        return total;
    }
}
