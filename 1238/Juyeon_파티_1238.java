import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Juyeon_파티_1238 {

    private static int X;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        List<List<int[]>> graph = new ArrayList<>();
        List<List<int[]>> reverse = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
            reverse.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(from).add(new int[]{to, cost});
            reverse.get(to).add(new int[]{from, cost});
        }

        int[] dist = new int[N + 1];
        int[] reverseDist = new int[N + 1];

        getDist(graph, dist);
        getDist(reverse, reverseDist);

        int max = 0;

        for (int i = 1; i <= N; i++) {
            max = Math.max(max, dist[i] + reverseDist[i]);
        }

        System.out.println(max);
    }

    private static void getDist(List<List<int[]>> graph, int[] dist) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[X] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.add(new int[]{X, 0});

        while (!pq.isEmpty()) {
            int[] e = pq.poll();
            int from = e[0];
            int cost = e[1];

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
    }
}
