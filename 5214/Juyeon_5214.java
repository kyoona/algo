import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Juyeon_5214 {

    private static List<List<Integer>> stations; // 연결된 튜브 저장
    private static List<List<Integer>> tubes; // 연결된 역 저장
    private static boolean[] visitedStation;
    private static boolean[] visitedTubes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        stations = new ArrayList<>();
        for (int i = 0; i <= N; i++) stations.add(new ArrayList<>());

        tubes = new ArrayList<>();
        for (int i = 0; i <= M; i++) tubes.add(new ArrayList<>());

        visitedStation = new boolean[N + 1];
        visitedTubes = new boolean[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < K; j++) {
                int v = Integer.parseInt(st.nextToken());

                stations.get(v).add(i);
                tubes.get(i).add(v);
            }
        }

        BFS(N);
    }

    private static void BFS(int to) {
        Queue<int[]> q = new LinkedList<>();

        q.add(new int[]{1, 1});
        visitedStation[1] = true;

        while (!q.isEmpty()) {
            int[] e = q.poll();

            if (e[0] == to) {
                System.out.println(e[1]);
                return;
            }

            for (int tube : stations.get(e[0])) {
                if (visitedTubes[tube]) continue;
                visitedTubes[tube] = true;

                for (int next : tubes.get(tube)) {
                    if (!visitedStation[next]) {
                        if (next == to) {
                            System.out.println(e[1] + 1);
                            return;
                        }
                        visitedStation[next] = true;
                        q.add(new int[]{next, e[1] + 1});
                    }
                }
            }
        }

        System.out.println(-1);
    }
}
