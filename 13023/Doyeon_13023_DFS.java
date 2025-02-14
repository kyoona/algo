import java.util.*;

public class Main {
    static List<List<Integer>> graph;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 정점 개수
        int M = sc.nextInt(); // 간선 개수

        // 그래프 초기화
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) { // 정점은 1-based index 사용
            graph.add(new ArrayList<>());
        }

        // 간선 정보 입력받기
        for (int i = 0; i < M; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            // 양방향 그래프
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        // 방문 여부 배열 초기화
        visited = new boolean[N + 1];

        // 모든 정점에 대해 DFS 실행
        for (int i = 1; i <= N; i++) {
            Arrays.fill(visited, false);
            if (dfs(i, 1) >= 5) { // depth가 5이상일 때
               System.out.println(1);
               return;
            }
        }
        System.out.println(0);

    }

    // DFS 메서드 (재귀)
    static int dfs(int start, int depth) {
        visited[start] = true;  // 현재 노드를 방문 처리

        int ans = depth; // 현재 depth를 (반환할) 초기 최대 깊이로 설정
        for(Integer to : graph.get(start))  {
            if (!visited[to]) {  // 방문하지 않은 노드만 탐색
                ans = Math.max(ans, dfs(to, depth + 1));
            }
        }

        if(ans < 5)  // 최대 깊이가 5 미만이라면
            visited[start] = false; // 방문 해제 (다른 경로 탐색을 위해)

        return ans;
    }
}