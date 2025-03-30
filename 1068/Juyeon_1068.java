import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Juyeon_1068 {

    private static List<List<Integer>> tree;
    private static boolean[] visited;
    private static int count;
    private static int remove;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        tree = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            tree.add(new ArrayList<>());
        }

        visited = new boolean[N];

        count = 0;

        st = new StringTokenizer(br.readLine());
        int root = -1;

        for (int i = 0; i < N; i++) {
            int e = Integer.parseInt(st.nextToken());

            if (e != -1) {
                tree.get(i).add(e);
                tree.get(e).add(i);
            } else {
                root = i;
            }
        }

        remove = Integer.parseInt(br.readLine());

        if (root == remove) System.out.println(0);
        else {
            DFS(root);
            System.out.println(count);
        }
    }

    private static void DFS(int x) {
        visited[x] = true;

        int temp = 0;

        for (int e : tree.get(x)) {
            if (!visited[e] && e != remove) {
                temp++;
                DFS(e);
            }
        }

        if (temp == 0) count++;
    }
}
