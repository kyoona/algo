import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Juyeon_11437 {

    private static List<List<Integer>> tree;
    private static int[] depth;
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        tree = new ArrayList<>();
        depth = new int[N + 1];
        parent = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree.get(a).add(b);
            tree.get(b).add(a);
        }

        init(0, 1, 1);

        int M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            bw.write(LCA(a, b) + "\n");
        }

        bw.flush();
        bw.close();
    }

    // depth, parent 초기화
    private static void init(int prev, int curr, int dep) {
        depth[curr] = dep;
        parent[curr] = prev;

        for (int next : tree.get(curr)) {
            if (next != prev) init(curr, next, dep + 1);
        }
    }

    private static int LCA(int a, int b) {
        while (depth[a] > depth[b]) {
            a = parent[a];
        }

        while (depth[a] < depth[b]) {
            b = parent[b];
        }

        while (a != b) {
            a = parent[a];
            b = parent[b];
        }

        return a;
    }
}
