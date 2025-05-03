import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Juyeon_1202 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<int[]> jewels = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int mass = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            jewels.add(new int[]{mass, value});
        }

        jewels.sort((o1, o2) -> o1[0] - o2[0]);

        int[] bags = new int[K];

        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(bags);

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);

        long answer = 0;
        int idx = 0;

        for (int i = 0; i < K; i++) {
            int cap = bags[i];

            while (idx < N && jewels.get(idx)[0] <= cap) {
                pq.add(jewels.get(idx)[1]);
                idx++;
            }

            if (!pq.isEmpty()) answer += pq.poll();
        }

        System.out.println(answer);
    }
}
