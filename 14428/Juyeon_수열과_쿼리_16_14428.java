import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Juyeon_수열과_쿼리_16_14428 {

    private static int[] arr;
    private static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        tree = new int[N * 4];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        arr[0] = Integer.MAX_VALUE;

        init(1, N, 1);

        int M = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());

            int command = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if (command == 1) {
                arr[x] = y;
                update(1, N, 1, x);
            } else if (command == 2) {
                sb.append(find(1, N, 1, x, y)).append("\n");
            }
        }

        System.out.println(sb);
    }

    // 트리에 idx 저장
    private static void init(int s, int e, int node) {
        if (s == e) {
            tree[node] = s;
            return;
        }

        int mid = (s + e) / 2;
        init(s, mid, node * 2);
        init(mid + 1, e, node * 2 + 1);

        tree[node] = getMinIdx(tree[node * 2], tree[node * 2 + 1]);
    }

    private static int getMinIdx(int x, int y) {
        if (arr[x] == arr[y]) return Math.min(x, y);
        return arr[x] < arr[y] ? x : y;
    }

    private static void update(int s, int e, int node, int idx) {
        if (idx < s || e < idx) return;

        if (s == e) {
            tree[node] = s;
            return;
        }

        int mid = (s + e) / 2;
        update(s, mid, node * 2, idx);
        update(mid + 1, e, node * 2 + 1, idx);

        tree[node] = getMinIdx(tree[node * 2], tree[node * 2 + 1]);
    }

    private static int find(int s, int e, int node, int x, int y) {
        if (y < s || e < x) return 0;
        if (x <= s && e <= y) return tree[node];

        int mid = (s + e) / 2;
        return getMinIdx(find(s, mid, node * 2, x, y), find(mid + 1, e, node * 2 + 1, x, y));
    }
}
