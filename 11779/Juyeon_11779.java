import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Juyeon_11779 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(from).add(new int[]{to, cost});
        }

        st = new StringTokenizer(br.readLine());

        int src = Integer.parseInt(st.nextToken());
        int dst = Integer.parseInt(st.nextToken());

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        int[] prev = new int[n + 1];

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.add(new int[]{src, 0});

        while (!pq.isEmpty()) {
            int[] e = pq.poll();
            int from = e[0];
            int cost = e[1];

            if (from == dst) break;

            if (dist[from] < cost) continue;

            for (int[] arr : graph.get(from)) {
                int to = arr[0];
                int weight = arr[1];

                if (cost + weight < dist[to]) {
                    dist[to] = cost + weight;
                    prev[to] = from;
                    pq.add(new int[]{to, cost + weight});
                }
            }
        }

        List<Integer> path = new ArrayList<>();
        for (int i = dst; i != 0; i = prev[i]) {
            path.add(i);
        }
        Collections.reverse(path);

        StringBuilder sb = new StringBuilder();
        sb.append(dist[dst]).append("\n").append(path.size()).append("\n");

        for (int i : path) {
            sb.append(i).append(" ");
        }

        System.out.println(sb);
    }
}
