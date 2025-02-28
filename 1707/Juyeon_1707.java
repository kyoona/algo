import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Juyeon_1707 {
    private static List<List<Integer>> graph;
    private static int[] nodes; // 미방문 0 / 방문 -1, 1

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int K = Integer.parseInt(br.readLine());

        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());

            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();

            for (int i = 0; i <= V; i++) {
                graph.add(new ArrayList<>());
            }

            nodes = new int[V + 1];
            boolean isBipartite = true;

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());

                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            for (int i = 1; i <= V; i++) {
                if (nodes[i] == 0) {
                    if (!BFS(i)) {
                        isBipartite = false;
                        break;
                    }
                }
            }

            sb.append(isBipartite ? "YES\n" : "NO\n");
        }

        System.out.println(sb.toString().trim());
    }

    private static boolean BFS(int x) {
        Queue<Integer> q = new LinkedList<>();
        q.add(x);
        nodes[x] = 1;

        while (!q.isEmpty()) {
            int e = q.poll();

            for (int i : graph.get(e)) {
                if (nodes[i] == 0) {
                    nodes[i] = -nodes[e];
                    q.add(i);
                } else if (nodes[i] == nodes[e]) return false;
            }
        }

        return true;
    }
}
