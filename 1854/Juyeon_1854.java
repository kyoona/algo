import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Juyeon_1854 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<List<int[]>> graph = new ArrayList<>();
        PriorityQueue<Integer>[] dist = new PriorityQueue[n + 1];

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
            dist[i] = new PriorityQueue<>((o1, o2) -> o2 - o1);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new int[]{b, c});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.add(new int[]{1, 0});
        dist[1].add(0);

        while (!pq.isEmpty()) {
            int[] e = pq.poll();
            int from = e[0];
            int cost = e[1];

            for (int[] arr : graph.get(from)) {
                int to = arr[0];
                int weight = arr[1];

                if (dist[to].size() < k) {
                    dist[to].add(cost + weight);
                    pq.add(new int[]{to, cost + weight});
                } else if (dist[to].peek() > cost + weight) {
                    dist[to].poll();
                    dist[to].add(cost + weight);
                    pq.add(new int[]{to, cost + weight});
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            if (dist[i].size() == k) sb.append(dist[i].peek()).append("\n");
            else sb.append("-1\n");
        }

        System.out.println(sb);
    }
}
