import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Juyeon_15683 {

    private static int N, M;
    private static int blind = Integer.MAX_VALUE;
    private static final List<int[]> cctvs = new ArrayList<>();

    // 위 오른쪽 아래 왼쪽
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    private static final int[][][] directions = {
            {},                                            // 0번 CCTV 없음
            {{0}, {1}, {2}, {3}},                          // 1번
            {{0, 2}, {1, 3}},                              // 2번
            {{0, 1}, {1, 2}, {2, 3}, {3, 0}},              // 3번
            {{0, 1, 2}, {1, 2, 3}, {2, 3, 0}, {3, 0, 1}},  // 4번
            {{0, 1, 2, 3}}                                 // 5번
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] office = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                office[i][j] = Integer.parseInt(st.nextToken());

                if (1 <= office[i][j] && office[i][j] <= 5) {
                    cctvs.add(new int[]{i, j, office[i][j]});
                }
            }
        }

        DFS(0, office);
        System.out.println(blind);
    }

    private static void DFS(int depth, int[][] map) {
        if (depth == cctvs.size()) {
            blind = Math.min(blind, countBlind(map));
            return;
        }

        int[] cctv = cctvs.get(depth);

        for (int[] dirs : directions[cctv[2]]) {
            int[][] copy = copyMap(map);

            for (int d : dirs) {
                watch(cctv[0], cctv[1], d, copy);
            }

            DFS(depth + 1, copy);
        }
    }

    private static int countBlind(int[][] map) {
        int count = 0;

        for (int[] row : map) {
            for (int i : row) {
                if (i == 0) count++;
            }
        }

        return count;
    }

    private static int[][] copyMap(int[][] map) {
        int[][] copy = new int[N][M];

        for (int i = 0; i < N; i++) {
            copy[i] = Arrays.copyOf(map[i], M);
        }

        return copy;
    }

    private static void watch(int x, int y, int d, int[][] map) {
        int nx = x + dx[d];
        int ny = y + dy[d];

        while (0 <= nx && nx < N && 0 <= ny && ny < M && map[nx][ny] != 6) {
            if (map[nx][ny] == 0) {
                map[nx][ny] = -1;
            }

            nx += dx[d];
            ny += dy[d];
        }
    }
}
