import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Juyeon_1715 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> q = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            q.add(Integer.parseInt(br.readLine()));
        }

        int answer = 0;

        while (q.size() > 1) {
            int a = q.poll();
            int b = q.poll();

            q.add(a + b);
            answer += a + b;
        }

        System.out.println(answer);
    }
}
