import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Juyeon_2473 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        long min = Long.MAX_VALUE;
        int fst = 0, snd = 0, trd = 0;

        for (int i = 0; i < N - 2; i++) {
            int mid = i + 1;
            int r = N - 1;

            while (mid < r) {
                long sum = arr[i] + arr[mid] + arr[r];

                if (min > Math.abs(sum)) {
                    min = Math.abs(sum);
                    fst = i;
                    snd = mid;
                    trd = r;
                }

                if (sum == 0) break;
                else if (sum > 0) r--;
                else mid++;
            }
        }

        System.out.println(arr[fst] + " " + arr[snd] + " " + arr[trd]);
    }
}
