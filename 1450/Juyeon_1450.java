import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Juyeon_1450 {

    private static int C;
    private static int[] arr;
    private static List<Integer> right;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        List<Integer> left = new ArrayList<>();
        right = new ArrayList<>();

        comb(left, 0, N / 2, 0);
        comb(right, N / 2, N, 0);

        Collections.sort(right);

        int count = 0;
        int idx;
        int size = right.size();

        for (int i : left) {
            idx = upperbound(0, size - 1, i);
            count += idx + 1;
        }

        System.out.println(count);
    }

    private static void comb(List<Integer> list, int s, int e, int sum) {
        if (sum > C) return;

        if (s == e) {
            list.add(sum);
            return;
        }

        comb(list, s + 1, e, sum);
        comb(list, s + 1, e, sum + arr[s]);
    }

    private static int upperbound(int s, int e, int v) {
        while (s <= e) {
            int mid = (s + e) / 2;

            if (right.get(mid) <= C - v) {
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }

        return e;
    }
}
