import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Juyeon_2018 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int l = 0, r = 0, sum = 0, count = 0;

        while (r < N) {
            if (sum < N) {
                sum += ++r;
            } else if (sum > N) {
                sum -= l++;
            } else {
                count++;
                sum += ++r;
            }
        }

        System.out.println(count + 1);
    }
}
