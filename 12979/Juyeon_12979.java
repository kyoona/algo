public class Juyeon_12979 {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;

        int pos = 1;
        int idx = 0;

        while (pos <= n) {
            if (idx >= stations.length || pos < stations[idx] - w) {
                answer++;
                pos += 2 * w + 1;
            } else {
                pos = stations[idx++] + w + 1;
            }
        }

        return answer;
    }
}
