import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Juyeon_1516 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        int[] time = new int[N + 1];
        int[] in = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            time[i] = Integer.parseInt(st.nextToken());

            while (true) {
                int v = Integer.parseInt(st.nextToken());

                if (v == -1) break;
                else {
                    graph.get(v).add(i);
                    in[i]++;
                }
            }
        }

        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            if (in[i] == 0) q.add(i);
        }

        int[] leadTime = new int[N + 1];

        while (!q.isEmpty()) {
            int e = q.poll();

            for (int i : graph.get(e)) {
                leadTime[i] = Math.max(leadTime[i], leadTime[e] + time[e]);
                if (--in[i] == 0) q.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            sb.append(leadTime[i] + time[i]).append("\n");
        }

        System.out.println(sb);
    }
}
