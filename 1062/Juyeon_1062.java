import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Juyeon_1062 {

    private static int K, max = 0;
    private static String[] list;
    private static final boolean[] visited = new boolean[26];
    private static final char[] essential = {'a', 'n', 't', 'i', 'c'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (K < 5) {
            System.out.println(0);
            return;
        } else if (K == 26) {
            System.out.println(N);
            return;
        }

        list = new String[N];

        for (char c : essential) {
            visited[c - 'a'] = true;
        }

        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            list[i] = word.substring(4, word.length() - 4);
        }

        DFS(0, 0);
        System.out.println(max);
    }

    private static void DFS(int idx, int depth) {
        if (depth == K - 5) {
            int readable = 0;

            for (String word : list) {
                boolean canRead = true;

                for (int i = 0; i < word.length(); i++) {
                    if (!visited[word.charAt(i) - 'a']) {
                        canRead = false;
                        break;
                    }
                }

                if (canRead) readable++;
            }

            max = Math.max(max, readable);
            return;
        }

        for (int i = idx; i < 26; i++) {
            if (!visited[i]) {
                visited[i] = true;
                DFS(i + 1, depth + 1);
                visited[i] = false;
            }
        }
    }
}
