import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Juyeon_5430 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            Deque<Integer> deque = new ArrayDeque<>();
            sb.append(AC(deque)).append("\n");
        }

        System.out.println(sb);
    }

    private static String AC(Deque<Integer> deque) throws IOException {
        String commands = br.readLine();
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), "[],");

        for (int i = 0; i < n; i++) {
            deque.add(Integer.parseInt(st.nextToken()));
        }

        boolean flag = true;

        for (char command : commands.toCharArray()) {
            if (command == 'R') {
                flag = !flag;
            } else {
                if (deque.isEmpty()) return "error";

                if (flag) deque.pollFirst();
                else deque.pollLast();
            }
        }

        return print(deque, flag);
    }

    private static String print(Deque<Integer> deque, boolean flag) {
        StringBuilder sb = new StringBuilder();

        sb.append("[");

        if (!deque.isEmpty()) {
            if (flag) {
                sb.append(deque.pollFirst());
                while (!deque.isEmpty()) sb.append(",").append(deque.pollFirst());
            } else {
                sb.append(deque.pollLast());
                while (!deque.isEmpty()) sb.append(",").append(deque.pollLast());
            }
        }

        sb.append("]");
        return sb.toString();
    }
}
