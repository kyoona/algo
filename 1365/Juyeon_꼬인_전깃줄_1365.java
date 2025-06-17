import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Juyeon_꼬인_전깃줄_1365 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        List<Integer> LIS = new ArrayList<>();

        for (int i : arr) {
            int idx = lowerBound(LIS, i);

            if (idx == LIS.size()) LIS.add(i);
            else LIS.set(idx, i);
        }

        System.out.println(N - LIS.size());
    }

    private static int lowerBound(List<Integer> list, int key) {
        int l = 0, r = list.size();

        while (l < r) {
            int mid = (l + r) / 2;

            if (list.get(mid) < key) l = mid + 1;
            else r = mid;
        }

        return l;
    }
}
