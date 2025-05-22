import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Juyeon_9935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();
        String bomb = br.readLine();

        int len = bomb.length();

        for (int i = 0; i < str.length(); i++) {
            sb.append(str.charAt(i));

            if (sb.length() >= len) {
                boolean isBomb = true;

                for (int j = 0; j < len; j++) {
                    if (sb.charAt(sb.length() - len + j) != bomb.charAt(j)) {
                        isBomb = false;
                        break;
                    }
                }

                if (isBomb) sb.delete(sb.length() - len, sb.length());
            }
        }

        System.out.println(sb.isEmpty() ? "FRULA" : sb);
    }
}
