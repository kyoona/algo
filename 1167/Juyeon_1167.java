import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Juyeon_1167 {

    static HashMap<Integer, Integer>[] list;
    static boolean[] visited;
    static int node;
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int V = Integer.parseInt(br.readLine());

        list = new HashMap[V + 1];

        for (int i = 1; i <= V; i++) {
            list[i] = new HashMap();
        }

        for (int i = 1; i <= V; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());

            while (true) {
                int to = Integer.parseInt(st.nextToken());
                if (to == -1) break;
                int cost = Integer.parseInt(st.nextToken());

                list[from].put(to, cost);
            }
        }

        visited = new boolean[V + 1];
        DFS(1, 0);

        visited = new boolean[V + 1];
        DFS(node, 0);

        System.out.println(max);
    }

    private static void DFS(int x, int distance) {
        if (distance > max) {
            max = distance;
            node = x;
        }

        visited[x] = true;

        for (Map.Entry<Integer, Integer> entry : list[x].entrySet()) {
            int v = entry.getKey();
            int cost = entry.getValue();

            if (!visited[v]) {
                DFS(v, distance + cost);
            }
        }

    }
}

/*
5
1 3 2 -1
2 4 4 -1
3 1 2 4 3 -1
4 2 4 3 3 5 6 -1
5 4 6 -1

11
 */