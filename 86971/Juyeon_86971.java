import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Juyeon_86971 {
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        boolean[] visited;

        Map<Integer, ArrayList<Integer>> graph = new HashMap<>();

        for (int[] wire : wires) {
            int v1 =  wire[0];
            int v2 =  wire[1];

            graph.putIfAbsent(v1, new ArrayList<>());
            graph.putIfAbsent(v2, new ArrayList<>());

            graph.get(v1).add(v2);
            graph.get(v2).add(v1);
        }

        // 전선 하나씩 자르기
        for (int[] wire : wires) {
            int v1 =  wire[0];
            int v2 =  wire[1];

            // remove(v2) 로 하면 인덱스로 인식해서 틀림~!
            graph.get(v1).remove(Integer.valueOf(v2));
            graph.get(v2).remove(Integer.valueOf(v1));

            visited = new boolean[n + 1];

            int count = DFS(graph, visited, v1);
            int diff = Math.abs((n - count) - count);

            answer = Math.min(answer, diff);

            graph.get(v1).add(v2);
            graph.get(v2).add(v1);
        }

        return answer;
    }

    private int DFS(Map<Integer, ArrayList<Integer>> graph, boolean[] visited, int idx) {
        visited[idx] = true;
        int count = 1;

        for (int i : graph.get(idx)) {
            if (!visited[i]) {
                visited[i] = true;
                count += DFS(graph, visited, i);
            }
        }

        return count;
    }
}
