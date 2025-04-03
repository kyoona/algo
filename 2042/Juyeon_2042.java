import java.io.*;
import java.util.StringTokenizer;

public class Juyeon_2042 {
    private static long[] arr;
    private static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        arr = new long[N];
        tree = new long[N * 4];

        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        init(0, arr.length - 1, 1);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());

            int command = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            long y = Long.parseLong(st.nextToken());

            if (command == 1) {
                long diff = y - arr[x - 1];
                update(0, arr.length - 1, 1, x - 1, diff);
            } else {
                bw.write(query(0, arr.length - 1, 1, x - 1, y - 1) + "\n");
            }
        }

        bw.flush();
        bw.close();
    }

    private static long init(int s, int e, int node) {
        if (s == e) return tree[node] = arr[s];
        else {
            int mid = (s + e) / 2;
            return tree[node] = init(s, mid, node * 2) + init(mid + 1, e, node * 2 + 1);
        }
    }

    private static void update(int s, int e, int node, int idx, long diff) {
        // 범위 바깥이면 신경 쓸 필요 없어
        if (idx < s || idx > e) return;

        tree[node] += diff;

        // 리프노드라면 arr 정보도 업데이트 해줘
        if (s == e) {
            arr[idx] = tree[node];
            return;
        }

        // 하위 노드들도 바꿔줘
        int mid = (s + e) / 2;
        update(s, mid, node * 2, idx, diff);
        update(mid + 1, e, node * 2 + 1, idx, diff);
    }

    private static long query(int s, int e, int node, int l, long r) {
        // 범위 바깥이면 더할 게 없음
        if (r < s || l > e) return 0;
        else if (l <= s && e <= r) return tree[node];

        int mid = (s + e) / 2;
        return query(s, mid, node * 2, l, r) + query(mid + 1, e, node * 2 + 1, l, r);
    }
}
