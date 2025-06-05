import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Juyeon_전봇대_8986 {

    private static final List<Long> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            list.add(Long.parseLong(st.nextToken()));
        }

        long s = 1, e = 1000000000;

        while (s + 3 <= e) {
            long l = (s * 2 + e) / 3;
            long r = (s + e * 2) / 3;

            long lCost = calc(l);
            long rCost = calc(r);

            if (lCost < rCost) {
                e = r;
            } else {
                s = l;
            }
        }

        long answer = Long.MAX_VALUE;

        for (long i = s; i <= e; i++) {
            answer = Math.min(answer, calc(i));
        }

        System.out.println(answer);
    }

    private static long calc(long v) {
        long sum = 0;

        for (int i = 0; i < list.size(); i++) {
            sum += Math.abs(i * v - list.get(i));
        }

        return sum;
    }
}
