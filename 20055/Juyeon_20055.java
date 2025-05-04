import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Juyeon_20055 {

    private static int N;
    private static int[] belt;
    private static boolean[] robots;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        belt = new int[2 * N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++) {
            belt[i] = Integer.parseInt(st.nextToken());
        }

        robots = new boolean[N];

        int step = 0;

        do {
            step++;

            rotate();
            move();

            if (belt[0] > 0) {
                robots[0] = true;
                belt[0]--;
            }

        } while (countZero() < K);

        System.out.println(step);
    }

    private static void rotate() {
        // 벨트 회전
        int temp = belt[2 * N - 1];

        for (int i = 2 * N - 1; i > 0; i--) {
            belt[i] = belt[i - 1];
        }

        belt[0] = temp;

        // 로봇 회전
        for (int i = N - 1; i > 0; i--) {
            robots[i] = robots[i - 1];
        }

        robots[0] = false;
        robots[N - 1] = false;
    }

    private static void move() {
        for (int i = N - 2; i >= 0; i--) {
            if (robots[i] && !robots[i + 1] && belt[i + 1] > 0) {
                robots[i] = false;
                robots[i + 1] = true;
                belt[i + 1]--;
            }
        }

        robots[N - 1] = false;
    }

    private static int countZero() {
        int count = 0;

        for (int i : belt) {
            if (i == 0) count++;
        }

        return count;
    }
}
