import java.util.Arrays;

public class Juyeon_43236 {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;

        Arrays.sort(rocks);

        int l = 0;
        int r = distance;

        while (l <= r) {
            int mid = (l + r) / 2;
            int prev = 0;
            int removed = 0;

            for (int rock : rocks) {
                if (rock - prev < mid) {
                    removed++;
                } else {
                    prev = rock;
                }
            }

            if (distance - prev < mid) {
                removed++;
            }

            if (n < removed) {
                r = mid - 1;
            } else {
                answer = mid;
                l = mid + 1;
            }
        }

        return answer;
    }
}
