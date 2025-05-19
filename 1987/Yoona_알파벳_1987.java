package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Yoona_알파벳_1987 {

    public static Character[][] map;
    public static int r;
    public static int c;
    public static int[] dx = {0, 1, -1, 0};
    public static int[] dy = {1, 0, 0, -1};
    public static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new Character[r][c];
        for (int i = 0; i < r; i++) {
            String input = reader.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        dfs(0, 0, new HashSet<>());
        System.out.println(max);
    }

    public static void dfs(int x, int y, Set<Character> path){
        path.add(map[x][y]);
        max = Integer.max(max, path.size());

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(isInBound(nx, ny) && isNotDuplicated(nx, ny, path)){
                dfs(nx, ny, path);
                path.remove(map[nx][ny]);
            }
        }
    }

    public static boolean isInBound(int x, int y){
        return x >= 0 && x < r && y >= 0 && y < c;
    }

    public static boolean isNotDuplicated(int x, int y, Set<Character> path){
        return !path.contains(map[x][y]);
    }
}
