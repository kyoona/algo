import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Juyeon_1916 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        List<List<int[]>> graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new int[]{v, w});
        }

        st = new StringTokenizer(br.readLine());

        int src = Integer.parseInt(st.nextToken());
        int dst = Integer.parseInt(st.nextToken());

        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.add(new int[]{src, 0});

        while (!pq.isEmpty()) {
            int[] e = pq.poll();
            int from = e[0];
            int cost = e[1];

            if (from == dst) break;

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

        System.out.println(dist[dst]);
    }
}
