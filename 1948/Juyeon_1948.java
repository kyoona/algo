import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Juyeon_1948 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        List<List<int[]>> graph = new ArrayList<>();
        List<List<int[]>> reverse = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
            reverse.add(new ArrayList<>());
        }

        int[] in = new int[N + 1];

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(from).add(new int[]{to, cost});
            reverse.get(to).add(new int[]{from, cost});
            in[to]++;
        }

        st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        int[] dist = new int[N + 1];

        while (!q.isEmpty()) {
            int e = q.poll();

            for (int[] arr : graph.get(e)) {
                int to = arr[0];
                int cost = arr[1];

                dist[to] = Math.max(dist[to], dist[e] + cost);

                if (--in[to] == 0) q.add(to);
            }
        }

        sb.append(dist[end]).append("\n");

        boolean[] visited = new boolean[N + 1];

        q.add(end);
        visited[end] = true;

        int count = 0;

        while (!q.isEmpty()) {
            int e = q.poll();

            for (int[] arr : reverse.get(e)) {
                int to = arr[0];
                int cost = arr[1];

                if (dist[e] == dist[to] + cost) {
                    count++;

                    if (!visited[to]) {
                        visited[to] = true;
                        q.add(to);
                    }
                }
            }
        }

        sb.append(count);

        System.out.println(sb);
    }
}
