import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Juyeon_15686 {

    private static int M;
    private static List<int[]> chicken;
    private static List<int[]> house;
    private static boolean[] visited;
    private static int dist = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        chicken = new ArrayList<>();
        house = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int v = Integer.parseInt(st.nextToken());

                if (v == 1) house.add(new int[]{i, j});
                else if (v == 2) chicken.add(new int[]{i, j});
            }
        }

        visited = new boolean[chicken.size()];

        backtrack(0, 0);

        System.out.println(dist);
    }

    // idx 는 중복 조합 피하려고 넣는 시작 인덱스 값
    private static void backtrack(int idx, int depth) {
        if (depth == M) {
            dist = Math.min(dist, getDistance());
            return;
        }

        for (int i = idx; i < chicken.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                backtrack(i + 1, depth + 1);
                visited[i] = false;
            }
        }
    }

    private static int getDistance() {
        int total = 0;

        for (int[] h : house) {
            int min = Integer.MAX_VALUE;

            for (int i = 0; i < chicken.size(); i++) {
                if (visited[i]) {
                    int[] c = chicken.get(i);
                    int d = Math.abs(h[0] - c[0]) + Math.abs(h[1] - c[1]);
                    min = Math.min(min, d);
                }
            }

            total += min;
        }

        return total;
    }
}
