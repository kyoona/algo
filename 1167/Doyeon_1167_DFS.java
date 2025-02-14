import java.io.*;
import java.util.*;

public class Main {
    static int V; // 정점의 개수
    static List<int[]>[] graph; // 그래프 정보 (인접 리스트, {다음 정점, 가중치})
    static boolean[] visited; // 방문 여부
    static int maxDist = 0; // 최대 거리
    static int farthestNode = 0; // 가장 먼 노드

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());

        graph = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());
            while (true) {
                int adj = Integer.parseInt(st.nextToken());
                if (adj == -1) break; // 인접 노드가 -1이면 입력 종료
                int weight = Integer.parseInt(st.nextToken()); // 가중치
                graph[node].add(new int[]{adj, weight});
            }
        }

        // 임의의 노드(1)에서 가장 먼 노드 찾기 위해 DFS 수행
        visited = new boolean[V + 1];
        dfs(1, 0);

        // 첫 번째 DFS에서 가장 먼 노드를 찾았다면,
        // 그 노드에서 다시 DFS를 수행하여 최대 거리를 찾음
        visited = new boolean[V + 1];
        dfs(farthestNode, 0);

        System.out.println(maxDist);
    }

    static void dfs(int node, int dist) {
        visited[node] = true;

        if (dist > maxDist) {
            maxDist = dist;
            farthestNode = node;
        }

        // 현재 노드와 연결된 모든 간선을 탐색
        for (int[] edge : graph[node]) {
            int nextNode = edge[0];
            int weight = edge[1];
            if (!visited[nextNode]) {
                dfs(nextNode, dist + weight);
            }
        }
    }
}
