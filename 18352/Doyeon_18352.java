import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K, X; // 도시 개수(N), 도로 개수(M), 목표 거리(K), 출발 도시(X)
    static List<List<Integer>> graph; // 인접 리스트로 그래프 저장
    static int[] distance; // 최단 거리 저장 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph.get(A).add(B); // A에서 B로 가는 단방향 도로 추가
        }

        distance = new int[N + 1];
        Arrays.fill(distance, -1);

        bfs(X);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (distance[i] == K) {
                sb.append(i).append("\n");
            }
        }

        System.out.println(sb.length() == 0 ? "-1" : sb.toString());
    }

    // BFS를 사용하여 최단 거리 탐색
    static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        distance[start] = 0;

        while (!queue.isEmpty()) {
            int now = queue.poll(); // 현재 탐색할 도시

            // 현재 도시에서 이동 가능한 모든 도시 탐색
            for (int next : graph.get(now)) {
                if (distance[next] == -1) {
                    distance[next] = distance[now] + 1;
                    queue.add(next);
                }
            }
        }
    }
}
