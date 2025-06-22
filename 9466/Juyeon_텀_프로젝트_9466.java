import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Juyeon_텀_프로젝트_9466 {

    private static int[] arr;
    private static boolean[] visited, done;
    private static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());

            arr = new int[n + 1];
            visited = new boolean[n + 1];
            done = new boolean[n + 1];
            count = 0;

            st = new StringTokenizer(br.readLine());

            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= n; i++) {
                if (!visited[i]) DFS(i);
            }

            sb.append(n - count).append("\n");
        }

        System.out.println(sb);
    }

    private static void DFS(int curr) {
        visited[curr] = true;
        int next = arr[curr];

        if (!visited[next]) DFS(next);
        else if (!done[next]) {
            count++;

            for (int i = next; i != curr; i = arr[i]) {
                count++;
            }
        }

        done[curr] = true;
    }
}
