import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Juyeon_14503 {

    private static int N, M;
    private static int[][] map;
    private static int count = 1;

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        DFS(r, c, d);
        System.out.println(count);
    }

    private static void DFS(int x, int y, int d) {
        // 청소한 구역 인덱싱
        map[x][y] = -1;

        for (int i = 0; i < 4; i++) {
            d = (d + 3) % 4;
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx >= 0 && ny >= 0 && nx < N && ny < M && map[nx][ny] == 0) {
                count++;
                DFS(nx, ny, d);
                return;
            }
        }

        int back = (d + 2) % 4;
        int nx = x + dx[back];
        int ny = y + dy[back];

        if (nx >= 0 && ny >= 0 && nx < N && ny < M && map[nx][ny] != 1) {
            DFS(nx, ny, d);
        }
    }
}
