package org.example;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Yoona_달팽이_1913 {

    public static final int DOWN = 0, RIGHT = 1, UP = 2, LEFT = 3;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int k = Integer.parseInt(scanner.nextLine());

        int[][] map = new int[n][n];

        int r = 0;
        int c = 0;
        int dir = DOWN;
        int num = n * n;
        String result = "";
        while (true){
            if(map[r][c] != 0) {
                break;
            }

            map[r][c] = num--;
            if(map[r][c] == k){
                result = (r + 1) + " " + (c + 1);
            }

            if(dir == DOWN){
                if(r + 1 >= n || map[r + 1][c] != 0){
                    dir = RIGHT;
                    c++;
                }else {
                    r++;
                }
            } else if (dir == RIGHT) {
                if (c + 1 >= n || map[r][c + 1] != 0) {
                    dir = UP;
                    r--;
                } else {
                    c++;
                }

            } else if (dir == UP) {
                if (r - 1 < 0 || map[r - 1][c] != 0) {
                    dir = LEFT;
                    c--;
                } else {
                    r--;
                }

            } else if (dir == LEFT) {
                if (c - 1 < 0 || map[r][c - 1] != 0) {
                    dir = DOWN;
                    r++;
                } else {
                    c--;
                }
            }
        }


        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                writer.write(map[i][j] + " ");
            }
            writer.write("\n");
        }
        writer.write(result);
        writer.flush();
    }
}
