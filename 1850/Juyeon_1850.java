import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Juyeon_1850 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        long gcd = getGCD(a, b);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < gcd; i++) {
            sb.append("1");
        }

        System.out.println(sb);
    }

    private static long getGCD(long a, long b) {
        if (a < b) {
            long temp = a;
            a = b;
            b = temp;
        }

        while (b != 0) {
            long r = a % b;
            a = b;
            b = r;
        }

        return a;
    }
}
