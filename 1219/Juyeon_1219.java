import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Juyeon_1219 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<List<int[]>> graph = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(from).add(new int[]{to, cost});
        }

        st = new StringTokenizer(br.readLine());

        int[] income = new int[N];

        for (int i = 0; i < N; i++) {
            income[i] = Integer.parseInt(st.nextToken());
        }

        long[] dist = new long[N];
        Arrays.fill(dist, Long.MIN_VALUE);
        dist[S] = income[S];

        // 음수 사이클이 도착지까지 전파되려면, 최악의 경우 N + N번까지 ( N + 100번만 해도 통과는 되더라~)
        for (int i = 0; i < N + N; i++) {
            for (int j = 0; j < N; j++) {
                if (dist[j] == Long.MIN_VALUE) continue;

                for (int[] edge : graph.get(j)) {
                    int to = edge[0];
                    int cost = edge[1];

                    if (dist[j] == Long.MAX_VALUE) dist[to] = Long.MAX_VALUE;
                    else if (dist[j] - cost + income[to] > dist[to]) {
                        dist[to] = dist[j] - cost + income[to];
                        if (i >= N) dist[to] = Long.MAX_VALUE;
                    }
                }
            }
        }

        if (dist[E] == Long.MAX_VALUE) System.out.println("Gee");
        else if (dist[E] == Long.MIN_VALUE) System.out.println("gg");
        else System.out.println(dist[E]);
    }
}
