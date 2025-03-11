public class Juyeon_12985 {
    public int solution(int n, int a, int b) {
        int answer = 0;

        while (a != b) {
//        while (Math.abs(a - b) >= 1) {
            a = a / 2 + a % 2;
            b = b / 2 + b % 2;

            answer++;
        }

        return answer;
    }
}
