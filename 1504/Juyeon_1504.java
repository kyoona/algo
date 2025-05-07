import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Juyeon_1504 {

    private static int N;
    private static List<List<int[]>> graph;
    private static final int INF = 200000001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new int[]{b, c});
            graph.get(b).add(new int[]{a, c});
        }

        st = new StringTokenizer(br.readLine());

        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int dist1 = 0;
        int dist2 = 0;

        dist1 += getDist(1, v1);
        dist1 += getDist(v1, v2);
        dist1 += getDist(v2, N);

        dist2 += getDist(1, v2);
        dist2 += getDist(v2, v1);
        dist2 += getDist(v1, N);

        System.out.println(dist1 >= INF && dist2 >= INF ? -1 : Math.min(dist1, dist2));
    }

    private static int getDist(int s, int e) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);

        pq.add(new int[]{s, 0});
        dist[s] = 0;

        while (!pq.isEmpty()) {
            int[] v = pq.poll();
            int from = v[0];
            int cost = v[1];

            if (cost > dist[from]) continue;

            for (int[] arr : graph.get(from)) {
                int to = arr[0];
                int weight = arr[1];

                if (dist[to] > cost + weight) {
                    dist[to] = cost + weight;
                    pq.add(new int[]{to, cost + weight});
                }
            }
        }

        return dist[e];
    }
}
