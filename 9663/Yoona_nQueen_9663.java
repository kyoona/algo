package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Yoona_nQueen_9663 {

    public static int[] board;
    public static int n;
    public static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());

        board = new int[n];

        search(0);

        System.out.println(ans);
    }

    public static void search(int row) {
        if (row == n) {
            ans++;
            return;
        }

        for (int col = 0; col < n; col++) {
            board[row] = col;
            if (isSafe(row)) {
                search(row + 1);
            }
        }
    }

    public static boolean isSafe(int row) {
        for (int i = 0; i < row; i++) {
            if (board[i] == board[row] || Math.abs(board[i] - board[row]) == row - i) {
                return false;
            }
        }
        return true;
    }
}
