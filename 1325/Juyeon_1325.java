import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Juyeon_1325 {
    private static List<List<Integer>> graph;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            graph.get(B).add(A);
        }

        int[] count = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            count[i] = BFS(i);
        }

        int max = Arrays.stream(count).max().getAsInt();

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            if (count[i] == max) sb.append(i).append(" ");
        }

        System.out.println(sb.toString().trim());
    }

    private static int BFS(int x) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];

        q.add(x);
        visited[x] = true;
        int count = 1;

        while (!q.isEmpty()) {
            int e = q.poll();

            for (int i : graph.get(e)) {
                if (!visited[i]) {
                    visited[i] = true;
                    count++;
                    q.add(i);
                }
            }
        }

        return count;
    }
}
