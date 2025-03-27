import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Juyeon_1253 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        long[] arr = new long[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);

        int count = 0;

        for (int i = 0; i < N; i++) {
            long v = arr[i];

            int l = 0;
            int r = N - 1;

            while (l < r) {
                if (arr[l] + arr[r] > v) r--;
                else if (arr[l] + arr[r] < v) l++;
                else {
                    if (l != i && r != i) {
                        count++;
                        break;
                    } else if (l == i) l++;
                    else r--;
                }
            }
        }

        System.out.println(count);
    }
}
