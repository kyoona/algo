import java.io.*;
import java.util.StringTokenizer;

public class Juyeon_11505 {

    private static long[] arr;
    private static long[] tree;
    private static final int MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        arr = new long[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        tree = new long[N * 4];

        init(0, N - 1, 1);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());

            int command = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            long y = Long.parseLong(st.nextToken());

            if (command == 1) {
                update(0, N - 1, 1, x - 1, y);
            } else {
                bw.write(query(0, N - 1, 1, x - 1, (int) y - 1) + "\n");
            }
        }

        bw.flush();
        bw.close();
    }

    private static long init(int s, int e, int node) {
        if (s == e) return tree[node] = arr[s] % MOD;

        int mid = (s + e) / 2;
        return tree[node] = (init(s, mid, node * 2) * init(mid + 1, e, node * 2 + 1)) % MOD;
    }

    private static long update(int s, int e, int node, int idx, long v) {
        if (idx < s || e < idx) return tree[node];

        if (s == e) return tree[node] = v % MOD;

        int mid = (s + e) / 2;

        return tree[node] = (update(s, mid, node * 2, idx, v) * update(mid + 1, e, node * 2 + 1, idx, v)) % MOD;
    }

    private static long query(int s, int e, int node, int l, double r) {
        if (r < s || e < l) return 1;
        else if (l <= s && e <= r) return tree[node];

        int mid = (s + e) / 2;
        return (query(s, mid, node * 2, l, r) * query(mid + 1, e, node * 2 + 1, l, r)) % MOD;
    }
}
