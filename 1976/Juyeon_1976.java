import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Juyeon_1976 {

    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        parent = new int[N];

        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int e = Integer.parseInt(st.nextToken());

                if (e == 1) union(i, j);
            }
        }

        boolean isPossible = true;

        st = new StringTokenizer(br.readLine());

        int x = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M - 1; i++) {

            int y = Integer.parseInt(st.nextToken());

            if (find(x - 1) != find(y - 1)) {
                isPossible = false;
                break;
            }
        }

        System.out.println(isPossible ? "YES" : "NO");
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) parent[y] = x;
    }

    private static int find(int x) {
        if (x == parent[x]) return x;
        else return parent[x] = find(parent[x]);
    }
}
