import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Juyeon_9663 {

    private static int N, count;
    private static int[] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        board = new int[N];

        backtrack(0);

        System.out.println(count);
    }

    private static void backtrack(int col) {
        if (col == N) {
            count++;
            return;
        }

        for (int row = 0; row < N; row++) {
            board[col] = row;

            if (isValid(col)) {
                backtrack(col + 1);
            }
        }
    }

    private static boolean isValid(int col) {
        for (int i = 0; i < col; i++) {
            if (board[col] == board[i]) return false;

            if (Math.abs(col - i) == Math.abs(board[col] - board[i])) return false;
        }

        return true;
    }
}
