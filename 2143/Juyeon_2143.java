import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Juyeon_2143 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long T = Long.parseLong(br.readLine());

        int n = Integer.parseInt(br.readLine());
        int[] A = readArr(br, n);
        List<Long> sumA = getSums(A);

        int m = Integer.parseInt(br.readLine());
        int[] B = readArr(br, m);
        Map<Long, Long> sumB = getSumCounts(B);

        long count = 0;

        for (long a : sumA) {
            count += sumB.getOrDefault(T - a, 0L);
        }

        System.out.println(count);
    }

    private static int[] readArr(BufferedReader br, int size) throws IOException {
        int[] arr = new int[size];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < size; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        return arr;
    }

    private static List<Long> getSums(int[] arr) {
        List<Long> result = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            long sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                result.add(sum);
            }
        }

        return result;
    }

    private static Map<Long, Long> getSumCounts(int[] arr) {
        Map<Long, Long> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            long sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                map.put(sum, map.getOrDefault(sum, 0L) + 1);
            }
        }

        return map;
    }
}
