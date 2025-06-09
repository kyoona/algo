import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Juyeon_개구리_점프_17619 {

    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        int[][] logs = new int[N][3]; // [idx, x1, x2]
        parent = new int[N + 1];

        for (int i = 1; i <= N; i++) parent[i] = i;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            // idx
            logs[i][0] = i + 1;
            logs[i][1] = Integer.parseInt(st.nextToken());
            logs[i][2] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(logs, ((o1, o2) -> o1[1] - o2[1]));

        // x2 좌표
        int max = logs[0][2];

        for (int i = 1; i < N; i++) {
            if (logs[i][1] <= max) {
                union(logs[i - 1][0], logs[i][0]);
                max = Math.max(max, logs[i][2]);
            } else {
                max = logs[i][2];
            }
        }

        StringBuilder sb = new StringBuilder();

        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            sb.append(find(u) == find(v) ? "1\n" : "0\n");
        }

        System.out.println(sb);
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) parent[y] = x;
    }

    private static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }
}
