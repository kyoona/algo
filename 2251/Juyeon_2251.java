import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Juyeon_2251 {
    private static int[] water;
    private static boolean[][] checked;
    private static boolean[] answer;

    private static final int[] sender = {0, 0, 1, 1, 2, 2};
    private static final int[] receiver = {1, 2, 0, 2, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        water = new int[3];

        water[0] = Integer.parseInt(st.nextToken());
        water[1] = Integer.parseInt(st.nextToken());
        water[2] = Integer.parseInt(st.nextToken());

        checked = new boolean[201][201];
        answer = new boolean[201];

        BFS();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < answer.length; i++) {
            if (answer[i]) sb.append(i).append(" ");
        }

        System.out.println(sb.toString().trim());
    }

    private static void BFS() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        checked[0][0] = true;
        answer[water[2]] = true;

        while (!q.isEmpty()) {
            int[] e = q.poll();
            int A = e[0];
            int B = e[1];
            int C = water[2] - A - B;

            for (int i = 0; i < 6; i++) {
                int[] arr = {A, B, C};
                arr[receiver[i]] += arr[sender[i]];
                arr[sender[i]] = 0;

                // 물이 넘칠 때
                if (arr[receiver[i]] > water[receiver[i]]) {
                    arr[sender[i]] = arr[receiver[i]] - water[receiver[i]];
                    arr[receiver[i]] = water[receiver[i]];
                }

                if (!checked[arr[0]][arr[1]]) {
                    checked[arr[0]][arr[1]] = true;
                    q.add(new int[]{arr[0], arr[1]});

                    if (arr[0] == 0) answer[arr[2]] = true;
                }
            }
        }
    }
}
