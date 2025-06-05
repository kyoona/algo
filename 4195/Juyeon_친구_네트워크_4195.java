import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Juyeon_친구_네트워크_4195 {

    private static final Map<String, Integer> map = new HashMap<>();
    private static int[] parent;
    private static int[] size;
    private static int idx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int F = Integer.parseInt(br.readLine());

            map.clear();
            parent = new int[F * 2];
            size = new int[F * 2];
            idx = 0;

            for (int i = 0; i < F * 2; i++) {
                parent[i] = i;
                size[i] = 1;
            }

            for (int i = 0; i < F; i++) {
                st = new StringTokenizer(br.readLine());

                String a = st.nextToken();
                String b = st.nextToken();

                int aId = getId(a);
                int bId = getId(b);

                sb.append(union(aId, bId)).append("\n");
            }
        }

        System.out.println(sb);
    }

    private static int getId(String name) {
        return map.computeIfAbsent(name, e -> idx++);
    }

    private static int union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            parent[y] = x;
            size[x] += size[y];
        }

        return size[x];
    }

    private static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }

        return parent[x];
    }
}
