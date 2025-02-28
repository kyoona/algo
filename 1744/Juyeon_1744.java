import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Juyeon_1744 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> positive = new PriorityQueue<>((o1, o2) ->  o2 - o1);
        PriorityQueue<Integer> negative = new PriorityQueue<>();

        boolean zero = false;
        int one = 0;

        for (int i = 0; i < N; i++) {
            int k = Integer.parseInt(br.readLine());

            if (k == 1) one++;
            else if (k > 0) positive.add(k);
            else if (k < 0) negative.add(k);
            else zero = true;
        }

        int answer = 0;

        while (positive.size() > 1) {
            int a = positive.poll();
            int b = positive.poll();

            answer += a * b;
        }

        if (!positive.isEmpty()) {
            answer += positive.poll();
        }

        while (negative.size() > 1) {
            int a = negative.poll();
            int b = negative.poll();

            answer += a * b;
        }

        if (!zero && !negative.isEmpty()) {
            answer += negative.poll();
        }

        System.out.println(answer + one);
    }
}
