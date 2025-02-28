import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<List<Integer>> graph;
    static int[] result; // 각 컴퓨터에서 해킹 가능한 수 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 신뢰하는 관계 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph.get(B).add(A); // 역방향 저장 (B 해킹 시 A도 해킹됨)
        }

        result = new int[N + 1];
        int maxCount = 0;

        // 모든 노드에 대해 BFS 탐색
        for (int i = 1; i <= N; i++) {
            int count = bfs(i);
            result[i] = count;
            maxCount = Math.max(maxCount, count);
        }

        // 가장 많이 해킹할 수 있는 컴퓨터 번호 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (result[i] == maxCount) {
                sb.append(i).append(" ");
            }
        }
        System.out.println(sb.toString().trim());
    }

    static int bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        queue.add(start);
        visited[start] = true;
        int count = 1;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int next : graph.get(now)) {
                if (!visited[next]) {
                    queue.add(next);
                    visited[next] = true;
                    count++;
                }
            }
        }
        return count;
    }
}
