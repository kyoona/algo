import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Juyeon_가운데를_말해요_1655 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> min = new PriorityQueue<>();
        PriorityQueue<Integer> max = new PriorityQueue<>((o1, o2) -> o2 - o1);

        StringBuilder sb = new StringBuilder();

        while (N-- > 0) {
            int i = Integer.parseInt(br.readLine());

            if (min.size() == max.size()) max.add(i);
            else min.add(i);

            if (!min.isEmpty() && !max.isEmpty() && min.peek() < max.peek()) {
                min.add(max.poll());
                max.add(min.poll());
            }

            sb.append(max.peek()).append("\n");
        }

        System.out.println(sb);
    }
}
