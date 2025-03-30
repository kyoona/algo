import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Juyeon_11725 {
    private static List<List<Integer>> tree;
    private static int[] parent;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        tree = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        parent = new int[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree.get(a).add(b);
            tree.get(b).add(a);
        }

        DFS(1);

        StringBuilder sb = new StringBuilder();

        for (int i = 2; i <= N; i++) {
            sb.append(parent[i]).append("\n");
        }

        System.out.println(sb);
    }

    private static void DFS(int x) {
        visited[x] = true;

        for (int e : tree.get(x)) {
            if (!visited[e]) {
                parent[e] = x;
                DFS(e);
            }
        }
    }
}
