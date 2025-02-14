package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Yoona_2178 {

    public static int[] dx = {-1, 0, 0, 1};
    public static int[] dy = {0, 1, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = reader.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int m = Integer.parseInt(firstLine[1]);

        boolean[][] map = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            String input = reader.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = input.charAt(j) == '1';
            }
        }

        Deque<Coord> queue = new ArrayDeque();
        boolean[][] visit = new boolean[n][m];
        queue.add(new Coord(0, 0, 1));
        visit[0][0] = true;

        while (!queue.isEmpty()){
            Coord coord = queue.poll();
            if(coord.x == n - 1 && coord.y == m - 1){
                System.out.println(coord.weight);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int x = coord.x + dx[i];
                int y = coord.y + dy[i];
                if(x >= 0 && x < n && y >= 0 && y < m && map[x][y] && !visit[x][y]){
                    queue.add(new Coord(x, y, coord.weight + 1));
                    visit[x][y] = true;
                }
            }
        }
    }

    static class Coord{

        public int x;
        public int y;
        public int weight;

        public Coord(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }
    }
}
