import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] maze;
    static boolean[][] visited; // 방문 여부 체크
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maze = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                maze[i][j] = line.charAt(j) - '0';
            }
        }

        System.out.println(bfs(0, 0));
    }

    static int bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>(); // BFS를 위한 큐 생성
        queue.add(new int[]{x, y, 1});
        visited[x][y] = true; // 시작 위치 방문 처리

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];
            int dist = current[2];

            // 목표 지점 도달 시 거리 반환
            if (cx == N - 1 && cy == M - 1) return dist;

            for (int i = 0; i < 4; i++) {  // 네 방향 탐색
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    if (maze[nx][ny] == 1 && !visited[nx][ny]) {
                        queue.add(new int[]{nx, ny, dist + 1});
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        return -1;
    }
}
