package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Yoona_아기상어_16235 {

    public static int[][] map;
    public static int[] dx = {-1, 0, 0, 1};
    public static int[] dy = {0, -1, 1, 0};
    public static int n;

    public static Shark shark;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(reader.readLine());

        int fish = 0;
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(reader.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9){
                    shark = new Shark(i, j, 2, 0, 0);
                    map[i][j] = 0;
                }else if(map[i][j] > 0){
                    fish++;
                }
            }
        }

        while (fish-- > 0){
            Node eatNode = bfs(shark.x, shark.y);
            if(eatNode == null){
                break;
            }

            shark.move(eatNode.x, eatNode.y, eatNode.len);
            shark.eat();
            map[eatNode.x][eatNode.y] = 0;
        }

        System.out.println(shark.mc);
    }

    public static Node bfs(int x, int y){
        Queue<Node> queue = new ArrayDeque();
        boolean[][] visit = new boolean[n][n];

        queue.add(new Node(x, y, 0));
        visit[x][y] = true;

        Node eatNode = null;
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            if(eatNode != null && cur.len > eatNode.len){
                break;
            }

            if(map[cur.x][cur.y] > 0 && shark.w > map[cur.x][cur.y]){
                if(eatNode == null || cur.x < eatNode.x){ //아직 먹이가 없거나 더 위에 있을때
                    eatNode = new Node(cur.x, cur.y, cur.len);
                }else if(cur.x == eatNode.x){
                    eatNode.y = Integer.min(cur.y, eatNode.y);
                }
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (canGo(nx, ny, visit)) {
                    queue.add(new Node(nx, ny, cur.len + 1));
                    visit[nx][ny] = true;
                }
            }
        }

        return eatNode;
    }

    public static boolean canGo(int x, int y, boolean[][] visit){
        return x >= 0 && x < n && y >= 0 && y < n && !visit[x][y] && shark.w >= map[x][y];
    }

    static class Node{
        public int x;
        public int y;
        public int len;

        public Node(int x, int y, int len) {
            this.x = x;
            this.y = y;
            this.len = len;
        }
    }

    static class Shark{
        public int x;
        public int y;
        public int w;
        public int exp;
        public int mc;

        public Shark(int x, int y, int w, int exp, int mc) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.exp = exp;
            this.mc = mc;
        }

        public void eat(){
            exp++;
            if(exp >= w){
                w++;
                exp = 0;
            }
        }

        public void move(int x, int y, int len){
            this.x = x;
            this.y = y;
            this.mc += len;
        }
    }
}
