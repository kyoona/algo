import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Juyeon_1033 {

    private static List<List<int[]>> list;
    private static boolean[] visited;
    private static long[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        list = new ArrayList<>();
        for (int i = 0; i < N; i++) list.add(new ArrayList<>());

        long lcm = 1;

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());

            list.get(a).add(new int[]{b, p, q});
            list.get(b).add(new int[]{a, q, p});

            lcm *= (p * q / getGCD(p, q));
        }

        answer = new long[N];
        answer[0] = lcm;

        visited = new boolean[N];

        DFS(0);

        long gcd = answer[0];
        for (int i = 1; i < N; i++) {
            gcd = getGCD(gcd, answer[i]);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(answer[i] / gcd).append(" ");
        }

        System.out.println(sb);
    }

    private static long getGCD(long a, long b) {
        if (a < b) {
            long temp = a;
            a = b;
            b = temp;
        }

        while (b != 0) {
            long r = a % b;
            a = b;
            b = r;
        }

        return a;
    }

    private static void DFS(int node) {
        visited[node] = true;

        for (int[] arr : list.get(node)) {
            int next = arr[0];

            if (!visited[next]) {
                answer[next] = answer[node] * arr[2] / arr[1];
                DFS(next);
            }
        }
    }
}
