import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Stack;

public class Juyeon_후위_표기식_1918 {

    private static final Map<Character, Integer> PRIORITY = Map.of(
            '+', 1,
            '-', 1,
            '*', 2,
            '/', 2,
            '(', 0
    );

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String expressions = br.readLine();
        StringBuilder sb = new StringBuilder();

        Stack<Character> st = new Stack<>();

        for (int i = 0; i < expressions.length(); i++) {
            char c = expressions.charAt(i);

            switch (c) {
                case '+': case '-': case '*': case '/':
                    while (!st.isEmpty() && getPriority(st.peek()) >= getPriority(c)) {
                        sb.append(st.pop());
                    }
                    st.add(c);
                    break;

                case '(':
                    st.add(c);
                    break;

                case ')':
                    while (!st.isEmpty() && st.peek() != '(') {
                        sb.append(st.pop());
                    }
                    st.pop(); // '(' 제거
                    break;

                default:
                    sb.append(c);
            }
        }

        while (!st.isEmpty()) {
            sb.append(st.pop());
        }

        System.out.println(sb);
    }

    private static int getPriority(char op) {
        return PRIORITY.getOrDefault(op, -1);
    }
}
