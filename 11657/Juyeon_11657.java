import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Juyeon_11657 {

    private static List<List<int[]>> graph;
    private static long[] dist;
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

        dist = new long[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            graph.get(A).add(new int[]{B, C});
        }

        StringBuilder sb = new StringBuilder();

        if (findCycle()) sb.append(-1);
        else {
            for (int i = 2; i <= N; i++) {
                sb.append(dist[i] == Integer.MAX_VALUE ? -1 : dist[i]).append("\n");
            }
        }

        System.out.println(sb);
    }

    private static boolean findCycle() {
        boolean cycle = false;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (int[] edge : graph.get(j)) {
                    int to = edge[0];
                    int weight = edge[1];

                    if (dist[j] != Integer.MAX_VALUE && dist[j] + weight < dist[to]) {
                        dist[to] = dist[j] + weight;

                        if (i == N) cycle = true;
                    }
                }
            }
        }

        return cycle;
    }
}
