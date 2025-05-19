import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Juyeon_2108 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        Map<Integer, Integer> map = new HashMap<>();

        double total = 0;

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            total += arr[i];
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        Arrays.sort(arr);

        int mean = (int) Math.round(total / N);
        int median = arr[arr.length / 2];

        int mod;

        int max = 0;

        for (int i = 0; i < N; i++) {
            if (map.get(arr[i]) > max) {
                max = map.get(arr[i]);
            }
        }

        List<Integer> list = new ArrayList<>();

        for (int i : map.keySet()) {
            if (map.get(i) == max) {
                list.add(i);
            }
        }

        Collections.sort(list);

        if (list.size() > 1) mod = list.get(1);
        else mod = list.get(0);

        int range = Math.abs(arr[N - 1] - arr[0]);

        sb.append(mean).append("\n").append(median).append("\n").append(mod).append("\n").append(range);
        System.out.println(sb);
    }
}
