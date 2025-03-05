import java.io.*;


public class Doyeon_1300 {
    static long N, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Long.parseLong(br.readLine());
        k = Long.parseLong(br.readLine());

        // k번째 수를 찾기 위한 이진 탐색
        System.out.println(findKthNumber(N, k));
    }

    // 이진 탐색을 활용하여 k번째 수를 찾는 함수
    private static int findKthNumber(long N, long k) {
        long left = 1, right = N * N, answer = 0;

        while (left <= right) {
            long mid = left + (right - left) / 2;

            // mid 이하의 수가 k개 이상이면, mid는 k번째 수로 가능한 값
            if (countLessOrEqual((int) N, (int) mid) >= k) {
                answer = mid;
                right = mid - 1; // 더 작은 값들을 찾아보기 위해 범위 축소
            } else {
                left = mid + 1; // mid보다 큰 값을 찾아야 하므로 범위 확장
            }
        }

        return (int) answer;
    }

    // x 이하의 수가 몇 개인지 세는 함수
    private static int countLessOrEqual(int N, int x) {
        int count = 0;

        // 각 행에서 x 이하의 수가 몇 개인지 계산
        for (int i = 1; i <= N; i++) {
            // x 이하인 값은 i * j에서 j는 1부터 N까지므로, i * j <= x인 j의 개수는 x / i
            count += Math.min(N, x / i); // i번째 행에서 x 이하의 개수
        }

        return count;
    }
}