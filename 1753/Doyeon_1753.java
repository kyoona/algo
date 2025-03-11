import java.io.*;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static List<List<int[]>> graph;
    static int[] dist;
    static int V, E, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        K = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        // 최단 거리 배열 초기화 (INF로 설정)
        dist = new int[V + 1];
        Arrays.fill(dist, INF);

        // 간선 정보 입력
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new int[]{v, w});  // 방향 그래프이므로 한 방향으로만 저장
        }

        dijkstra(K);

        // 최단 거리 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            sb.append(dist[i] == INF ? "INF" : dist[i]).append("\n");
        }
        System.out.print(sb);
    }

    // 다익스트라 알고리즘
    static void dijkstra(int start) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{start, 0});
        dist[start] = 0;  // 시작 정점 거리 0 설정

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int now = cur[0];  // 현재 정점
            int nowDist = cur[1];  // 현재까지의 거리

            // 이미 처리된 노드라면 스킵
            if (dist[now] < nowDist) continue;

            // 현재 정점과 연결된 인접 노드 확인
            for (int[] next : graph.get(now)) {
                int nextVertex = next[0];
                int newDist = nowDist + next[1];

                // 더 짧은 거리 발견했을 때
                if (newDist < dist[nextVertex]) {
                    dist[nextVertex] = newDist;
                    pq.offer(new int[]{nextVertex, newDist});  // 우선순위 큐에 삽입
                }
            }
        }
    }
}
