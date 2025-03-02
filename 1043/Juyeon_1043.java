import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Juyeon_1043 {

    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        boolean[] truth = new boolean[N + 1];

        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < k; i++) {
            truth[Integer.parseInt(st.nextToken())] = true;
        }

        List<List<Integer>> party = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            party.add(new ArrayList<>());

            st = new StringTokenizer(br.readLine());

            int p = Integer.parseInt(st.nextToken());

            int x = Integer.parseInt(st.nextToken());
            party.get(i).add(x);

            for (int j = 1; j < p; j++) {
                int y = Integer.parseInt(st.nextToken());
                party.get(i).add(y);

                union(x, y);
            }
        }

        for (int i = 1; i <= N; i++) {
            if (truth[i]) truth[find(i)] = true;
        }

        int count = 0;

        for (int i = 0; i < M; i++) {
            k = find(party.get(i).get(0));
            if (!truth[k]) count++;
        }

        System.out.println(count);
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
