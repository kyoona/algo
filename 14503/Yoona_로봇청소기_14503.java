package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Yoona_로봇청소기_14503 {

    public static int n;
    public static int m;
    public static int[][] map;
    public static int[] dr = {-1, 0, 1, 0}; //북 동 남 서
    public static int[] dc = {0, 1, 0, -1};

    public static final int CLEAN = 2;
    public static final int WALL = 1;
    public static final int DIRTY = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(reader.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        map = new int[n][m]; //청소 x = true
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(reader.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;
        while (isRoad(r, c)){
            if(map[r][c] == DIRTY){
                map[r][c] = CLEAN;
                ans++;
            }

            boolean dFlag = false;
            for (int i = 0; i < 4; i++) {
                int nd = d - i - 1 >= 0 ? d - i - 1 : 3 - i + d;
                int nr = r + dr[nd];
                int nc = c + dc[nd];
                if(isRoad(nr, nc) && map[nr][nc] == DIRTY){
                    r = nr;
                    c = nc;
                    d = nd;
                    dFlag = true;
                    break;
                }
            }

            if(!dFlag){
                int back = d - 2 >= 0 ? d - 2 : 2 + d;
                r = r + dr[back];
                c = c + dc[back];
            }
        }

        System.out.println(ans);
    }

    public static boolean isRoad(int r, int c){
        return r >= 0 && r < n && c >= 0 && c < m && map[r][c] != WALL;
    }
}