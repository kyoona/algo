import java.util.Stack;

public class Juyeon_81303 {
    public String solution(int n, int k, String[] cmd) {
        Stack<Integer> st = new Stack<>();

        for (String str : cmd) {
            String[] command = str.split(" ");

            String c = command[0];

            switch (c) {
                case "U" -> k -= Integer.parseInt(command[1]);
                case "D" -> k += Integer.parseInt(command[1]);
                case "C" -> {
                    st.add(k);
                    if (k == --n) k--;
                }
                case "Z" -> {
                    if (st.pop() <= k) k++;
                    n++;
                }
            }
        }

        StringBuilder sb = new StringBuilder("O".repeat(n));

        while (!st.isEmpty()) {
            sb.insert(st.pop(), "X");
        }

        return sb.toString();
    }
}
