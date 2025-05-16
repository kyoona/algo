import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Juyeon_1987 {

    private static int R, C, answer = 0;
    private static char[][] map;
    private static boolean[] visited;

    private static final int[] dx = {1, 0, -1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[26];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        visited[map[0][0] - 'A'] = true;
        DFS(0, 0, 1);
        System.out.println(answer);
    }

    private static void DFS(int x, int y, int count) {
        boolean canGo = false;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || C <= nx || R <= ny) continue;

            char c = map[ny][nx];

            if (visited[c - 'A']) continue;

            canGo = true;
            visited[c - 'A'] = true;
            DFS(nx, ny, count + 1);
            visited[c - 'A'] = false;
        }

        if (!canGo) {
            answer = Math.max(answer, count);
        }
    }
}
