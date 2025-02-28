import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Juyeon_1541 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), "-");

        int answer = getValue(st.nextToken());

        while (st.hasMoreTokens()) {
            answer -= getValue(st.nextToken());
        }

        System.out.println(answer);
    }

    private static int getValue(String token) {
        try {
            return Integer.parseInt(token);
        } catch (NumberFormatException e) {     // if (token.contains("+"))
            return calc(token);
        }
    }

    private static int calc(String token) {
        int sum = 0;

        StringTokenizer add = new StringTokenizer(token, "+");

        while (add.hasMoreTokens()) {
            sum += Integer.parseInt(add.nextToken());
        }

        return sum;
    }

//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine(), "-");
//
//        int sum = Integer.MAX_VALUE;
//
//        while (st.hasMoreTokens()) {
//            int temp = 0;
//
//            StringTokenizer add = new StringTokenizer(st.nextToken(), "+");
//
//            while (add.hasMoreTokens()) {
//                temp += Integer.parseInt(add.nextToken());
//            }
//
//            if (sum == Integer.MAX_VALUE) {
//                sum = temp;
//            } else {
//                sum -= temp;
//            }
//        }
//
//        System.out.println(sum);
//    }
}
