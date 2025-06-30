import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Juyeon_책_나눠주기_9576 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            boolean[] marked = new boolean[N + 1];
            List<int[]> list = new ArrayList<>();

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                list.add(new int[]{a, b});
            }

            list.sort((o1, o2) -> o1[1] - o2[1]);

            int count = 0;

            for (int[] arr : list) {
                for (int i = arr[0]; i <= arr[1]; i++) {
                    if (!marked[i]) {
                        marked[i] = true;
                        count++;
                        break;
                    }
                }
            }

            sb.append(count).append("\n");
        }

        System.out.println(sb);
    }
}
