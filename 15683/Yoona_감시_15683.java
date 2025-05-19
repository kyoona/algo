package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Yoona_감시_15683 {

    public static int[][][] map;
    public static int n, m;

    public static List<Node> cctv = new ArrayList<>();
    public static int emptyCount = 0;

    public static int ans = Integer.MAX_VALUE;
    public static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m][2]; //CCTV종류, 방향(0,1,2,3)
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(reader.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j][0] = Integer.parseInt(st.nextToken());

                if(map[i][j][0] == 0) {
                    emptyCount++;
                } else if (map[i][j][0] >= 1 && map[i][j][0] <= 5) {
                    cctv.add(new Node(i, j));
                }
            }
        }

        search(0);
        System.out.println(ans);
    }

    public static void search(int idx){
        check();

        if (idx >= cctv.size()) {
            return;
        }

        Node node = cctv.get(idx);
        for (int i = 0; i < 4; i++) {
            map[node.x][node.y][1] = i; //방향 전환
            search(idx + 1);
        }
    }

    public static void check(){
        boolean[][] visit = new boolean[n][m];
        count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int cctvTyp = map[i][j][0];
                int dir = map[i][j][1];
                if(cctvTyp == 1) {
                    if (dir == 0){
                        dfs(i, j, 0, 1, visit);
                    } else if (dir == 1) {
                        dfs(i, j, 1, 0, visit);
                    } else if (dir == 2) {
                        dfs(i, j, 0, -1, visit);
                    } else if (dir == 3) {
                        dfs(i, j, -1, 0, visit);
                    }
                } else if (cctvTyp == 2) {
                    if (dir == 0 || dir == 2){
                        dfs(i, j, 0, 1, visit);
                        dfs(i, j, 0, -1, visit);
                    } else if (dir == 1 || dir == 3) {
                        dfs(i, j, 1, 0, visit);
                        dfs(i, j, -1, 0, visit);
                    }
                } else if (cctvTyp == 3) {
                    if(dir == 0 || dir == 1){
                        dfs(i, j, 0, 1, visit);
                    }
                    if(dir == 1 || dir == 2){
                        dfs(i, j, 1, 0, visit);
                    }
                    if(dir == 2 || dir == 3){
                        dfs(i, j, 0, -1, visit);
                    }
                    if(dir == 3 || dir == 0) {
                        dfs(i, j, -1, 0, visit);
                    }
                } else if (cctvTyp == 4) {
                    if(dir == 0 || dir == 1 || dir == 2){
                        dfs(i, j, 0, 1, visit);
                    }
                    if(dir == 1 || dir == 2 || dir == 3){
                        dfs(i, j, 1, 0, visit);
                    }
                    if(dir == 2 || dir == 3 || dir == 0){
                        dfs(i, j, 0, -1, visit);
                    }
                    if(dir == 3 || dir == 0 || dir == 1){
                        dfs(i, j, -1, 0, visit);
                    }
                } else if (cctvTyp == 5) {
                    dfs(i, j, 0, 1, visit);
                    dfs(i, j, 0, -1, visit);
                    dfs(i, j, 1, 0, visit);
                    dfs(i, j, -1, 0, visit);
                }
            }
        }

        ans = Integer.min(ans, emptyCount - count);
    }

    public static void dfs(int x, int y, int dx, int dy, boolean[][] visit){
        if(x < 0 || x >= n || y < 0 || y >= m || map[x][y][0] == 6){
            return;
        }

        if(map[x][y][0] == 0 && !visit[x][y]){
            count++;
        }
        visit[x][y] = true;

        dfs(x + dx, y + dy, dx, dy, visit);
    }

    static class Node {
        public int x;
        public int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
