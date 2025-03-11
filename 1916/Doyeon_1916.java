import java.io.*;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static List<List<int[]>> graph;
    static int[] dist;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        dist = new int[N + 1];
        Arrays.fill(dist, INF);

        // 버스 정보
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new int[]{v, w});
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(start);

        System.out.println(dist[end]);
    }

    // 다익스트라 알고리즘
    static void dijkstra(int start) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{start, 0});
        dist[start] = 0; // 시작 도시는 비용 0

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int now = cur[0];
            int nowCost = cur[1];

            // 이미 처리된 경우 스킵
            if (dist[now] < nowCost) continue;

            // 현재 도시와 연결된 인접 도시 확인
            for (int[] next : graph.get(now)) {
                int nextCity = next[0];
                int newCost = nowCost + next[1];

                // 더 적은 비용으로 갈 수 있다면
                if (newCost < dist[nextCity]) {
                    dist[nextCity] = newCost;
                    pq.offer(new int[]{nextCity, newCost}); // 우선순위 큐에 삽입
                }
            }
        }
    }
}
