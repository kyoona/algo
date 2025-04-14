import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Juyeon_1414 {

    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) parent[i] = i;

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);

        int len = 0;

        for (int i = 1; i <= N; i++) {
            String str = br.readLine();

            for (int j = 1; j <= N; j++) {
                int k = str.charAt(j - 1);

                if (k >= 97) k -= 96;
                else if (k == 48) k = 0;
                else k -= 38;

                if (k != 0) pq.add(new int[]{i, j, k});
                len += k;
            }
        }

        int count = 0;

        while (!pq.isEmpty()) {
            if (count == N - 1) break;

            int[] e = pq.poll();
            int u = e[0];
            int v = e[1];
            int w = e[2];

            if (find(u) != find(v)) {
                union(u, v);
                count++;
                len -= w;
            }
        }

        System.out.println(count == N - 1 ? len : -1);
    }

    private static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) parent[x] = y;
    }
}
