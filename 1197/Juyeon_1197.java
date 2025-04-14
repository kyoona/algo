import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Juyeon_1197 {

    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        parent = new int[V + 1];
        for (int i = 1; i <= V; i++) parent[i] = i;

        PriorityQueue<int[]> pq = new PriorityQueue<>(((o1, o2) -> o1[2] - o2[2]));

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            pq.add(new int[]{u, v, w});
        }

        int weight = 0;

        while (!pq.isEmpty()) {
            int[] edge = pq.poll();
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            if (find(u) != find(v)) {
                union(u, v);
                weight += w;
            }
        }

        System.out.println(weight);
    }

    private static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) parent[y] = x;
    }
}
