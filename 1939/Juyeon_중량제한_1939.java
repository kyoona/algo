import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Juyeon_중량제한_1939 {

    private static List<List<int[]>> graph;
    private static boolean[] visited;
    private static int from, to;
    private static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new int[]{v, w});
            graph.get(v).add(new int[]{u, w});

            max = Math.max(max, w);
        }

        st = new StringTokenizer(br.readLine());

        from = Integer.parseInt(st.nextToken());
        to = Integer.parseInt(st.nextToken());

        // binary search
        int l = 1;
        int r = max;
        int answer = 0;

        while (l <= r) {
            int mid = (l + r) / 2;

            visited = new boolean[N + 1];

            if (BFS(mid)) {
                answer = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        System.out.println(answer);
    }

    private static boolean BFS(int w) {
        Queue<Integer> q = new LinkedList<>();
        visited[from] = true;
        q.add(from);

        while (!q.isEmpty()) {
            int e = q.poll();

            if (e == to) return true;

            for (int[] arr : graph.get(e)) {
                int next = arr[0];
                int limit = arr[1];

                if (!visited[next] && limit >= w) {
                    visited[next] = true;
                    q.add(next);
                }
            }
        }

        return false;
    }
}
