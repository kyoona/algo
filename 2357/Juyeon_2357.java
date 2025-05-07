import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Juyeon_2357 {

    private static int[] arr;
    private static int[] minTree;
    private static int[] maxTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        minTree = new int[N * 4];
        maxTree = new int[N * 4];

        init(0, N - 1, 1);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(getMin(0, N - 1, 1, a - 1, b - 1)).append(" ").append(getMax(0, N - 1, 1, a - 1, b - 1)).append("\n");
        }

        System.out.println(sb);
    }

    private static void init(int s, int e, int node) {
        if (s == e) {
            minTree[node] = maxTree[node] = arr[s];
            return;
        }

        int mid = (s + e) / 2;
        init(s, mid, node * 2);
        init(mid + 1, e, node * 2 + 1);

        minTree[node] = Math.min(minTree[node * 2], minTree[node * 2 + 1]);
        maxTree[node] = Math.max(maxTree[node * 2], maxTree[node * 2 + 1]);
    }

    private static int getMin(int s, int e, int node, int l, int r) {
        if (r < s || e < l) return Integer.MAX_VALUE;
        else if (l <= s && e <= r) return minTree[node];

        int mid = (s + e) / 2;
        return Math.min(getMin(s, mid, node * 2, l, r), getMin(mid + 1, e, node * 2 + 1, l, r));
    }

    private static int getMax(int s, int e, int node, int l, int r) {
        if (r < s || e < l) return Integer.MIN_VALUE;
        else if (l <= s && e <= r) return maxTree[node];

        int mid = (s + e) / 2;
        return Math.max(getMax(s, mid, node * 2, l, r), getMax(mid + 1, e, node * 2 + 1, l, r));
    }
}
