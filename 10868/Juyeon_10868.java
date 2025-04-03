import java.io.*;
import java.util.StringTokenizer;

public class Juyeon_10868 {

    private static int[] arr;
    private static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        tree = new int[N * 4];

        init(0, arr.length - 1, 1);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            bw.write(getMin(0, arr.length - 1, 1, a - 1, b - 1) + "\n");
        }

        bw.flush();
        bw.close();
    }

    private static int init(int s, int e, int node) {
        if (s == e) return tree[node] = arr[s];

        int mid = (s + e) / 2;
        return tree[node] = Math.min(init(s, mid, node * 2), init(mid + 1, e, node * 2 + 1));
    }

    private static int getMin(int s, int e, int node, int l, int r) {
        if (r < s || e < l) return Integer.MAX_VALUE;
        else if (l <= s && e <= r) return tree[node];

        int mid = (s + e) / 2;
        return Math.min(getMin(s, mid, node * 2, l, r), getMin(mid + 1, e, node * 2 + 1, l, r));
    }
}
