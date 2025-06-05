public class Juyeon_92344 {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;

        int r = board.length;
        int c = board[0].length;

        int[][] diff = new int[r + 1][c + 1];

        for (int[] arr : skill) {
            int type = arr[0];
            int r1 = arr[1], c1 = arr[2], r2 = arr[3], c2 = arr[4], degree = arr[5];

            degree = (type == 1) ? -degree : degree;

            diff[r1][c1] += degree;
            diff[r2 + 1][c1] -= degree;
            diff[r1][c2 + 1] -= degree;
            diff[r2 + 1][c2 + 1] += degree;
        }

        for (int j = 0; j < c; j++) {
            for (int i = 0; i < r; i++) {
                diff[i + 1][j] += diff[i][j];
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                diff[i][j + 1] += diff[i][j];
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (board[i][j] + diff[i][j] > 0) answer++;
            }
        }

        return answer;
    }
}
