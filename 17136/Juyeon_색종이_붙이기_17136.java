import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Juyeon_색종이_붙이기_17136 {

    private static final int[][] map = new int[10][10];
    private static final int[] colored = {0, 5, 5, 5, 5, 5};
    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        DFS(0, 0);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    private static void DFS(int pos, int used) {
        if (used >= answer) return;

        // 탐색 완료
        if (pos == 100) {
            answer = used;
            return;
        }

        int y = pos / 10;
        int x = pos % 10;

        if (map[y][x] == 1) {
            for (int size = 5; size >= 1; size--) {
                if (colored[size] > 0 && canAttach(x, y, size)) {
                    attach(x, y, size, 0); // 색종이 붙임
                    colored[size]--;
                    DFS(pos + 1, used + 1);
                    attach(x, y, size, 1); // 다시 뗌
                    colored[size]++;
                }
            }
        } else {
            DFS(pos + 1, used);
        }
    }

    private static boolean canAttach(int x, int y, int size) {
        if (x + size > 10 || y + size > 10) return false;

        for (int i = y; i < y + size; i++) {
            for (int j = x; j < x + size; j++) {
                if (map[i][j] != 1) return false;
            }
        }

        return true;
    }

    private static void attach(int x, int y, int size, int state) {
        for (int i = y; i < y + size; i++) {
            for (int j = x; j < x + size; j++) {
                map[i][j] = state;
            }
        }
    }
}
