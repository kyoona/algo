import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Juyeon_11438 {

    private static List<List<Integer>> tree;
    private static int[] depth;
    private static int[][] parent;
    private static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        tree = new ArrayList<>();

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

        k = 0;
        int count = 1;
        while (count < N + 1) {
            count <<= 1;
            k++;
        }

        depth = new int[N + 1];
        parent = new int[N + 1][k];

        init(0, 1, 1);

        for (int i = 1; i < k; i++) {
            for (int j = 1; j <= N; j++) {
                parent[j][i] = parent[parent[j][i - 1]][i - 1];
            }
        }

        int M = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(LCA(a, b)).append("\n");
        }

        System.out.println(sb);
    }

    private static void init(int prev, int curr, int dep) {
        depth[curr] = dep;

        for (int next : tree.get(curr)) {
            if (next != prev) {
                init(curr, next, dep + 1);
                parent[next][0] = curr;
            }
        }
    }

    private static int LCA(int a, int b) {
        // b가 더 깊게 정렬
        if (depth[a] > depth[b]) {
            int temp = b;
            b = a;
            a = temp;
        }

        int diff = depth[b] - depth[a];

        // 깊이 맞추고
        for (int i = k; i >= 0; i--) {
            if (diff >= Math.pow(2, i)) {
                b = parent[b][i];
                diff -= (int) Math.pow(2, i);
            }
        }

        if (a == b) return a;

        // 공통 조상까지 올라옴
        for (int i = k - 1; i >= 0; i--) {
            if (parent[a][i] != parent[b][i]) {
                a = parent[a][i];
                b = parent[b][i];
            }
        }

        return parent[a][0];
    }
}
