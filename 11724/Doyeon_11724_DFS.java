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

        // 연결된 요소의 개수 구하기
        int connectedComponents = 0;

        // 모든 정점에 대해 DFS 실행
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) { // 아직 방문하지 않은 노드에 대해서만 DFS 시작
                connectedComponents++;
                dfs(i); // DFS 실행
            }
        }

        // 연결된 요소(그래프 컴포넌트) 개수 출력
        System.out.println(connectedComponents);

        sc.close();
    }

    // DFS 메서드 (재귀)
    static void dfs(int node) {
        // 방문 처리
        visited[node] = true;

        // 연결된 노드 탐색
        for (int next : graph.get(node)) {
            if (!visited[next]) {
                dfs(next); // 재귀적으로 DFS 호출
            }
        }
    }
}