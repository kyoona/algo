package org.example;

import java.util.*;

public class Yoona_67259 {
    public int[][] costMap;
    public boolean[][] visit;
    public int[][] map;
    public int n;
    public int[] dr = {1, -1, 0, 0};
    public int[] dc = {0, 0, 1, -1};
    public int[] dir = {0, 0, 1, 1};

    public int solution(int[][] board) {
        n = board.length;
        map = board;
        visit = new boolean[n][n];
        costMap = new int[n][n];

        for(int i = 0; i < n; i++){
            Arrays.fill(costMap[i], Integer.MAX_VALUE);
        }

        dfs(0, 0, -1, 0);

        return costMap[n-1][n-1];
    }

    public void dfs(int r, int c, int d, int cost){
        costMap[r][c] = Integer.min(costMap[r][c], cost);
        visit[r][c] = true;

        if(r == n-1 && c == n-1){
            return;
        }

        for(int i = 0; i < 4; i++){
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(checkBound(nr, nc) && map[nr][nc] == 0){
                int dCost = (d == -1 || dir[i] == d) ? 100 : 600;
                if(checkCanUpdate(nr, nc, dr[i], dc[i], cost + dCost)){
                    dfs(nr, nc, dir[i], cost + dCost);
                }
            }
        }
    }

    public boolean checkBound(int r, int c){
        return r >= 0 && r < n && c >= 0 && c < n;
    }

    public boolean checkCanUpdate(int r, int c, int dr, int dc, int cost){
        int nr = r + dr;
        int nc = c + dc;
        return !visit[r][c] || costMap[r][c] >= cost || (checkBound(nr, nc) && map[nr][nc] == 0 && costMap[nr][nc] >= cost + 100);
    }
}
