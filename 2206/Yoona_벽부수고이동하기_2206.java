package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Yoona_벽부수고이동하기_2206 {

    public static int N, M;
    public static int[][] map;
    public static int[] dr = {0, 1, -1, 0};
    public static int[] dc = {1, 0, 0, -1};
    public static int BREAK = 0, UNBREAK = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String input = reader.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        //모든 정점은 2번 방문할 수 있다 -> 벽을 이미 부수었을때, 아직 안부수었을때
        int ans = bfs(0, 0);

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    public static int bfs(int r, int c){
        boolean[][][] visit = new boolean[N][M][2]; //{벽 부숨, 벽 안부숨}
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(r, c, 1, UNBREAK));
        visit[r][c][UNBREAK] = true;

        while (!queue.isEmpty()){
            Node node = queue.poll();
            if(node.r == N - 1 && node.c == M - 1) return node.d;

            for (int i = 0; i < 4; i++) {
                int nr = node.r + dr[i];
                int nc = node.c + dc[i];

                if(isInBound(nr, nc) && !visit[nr][nc][node.breakFlag]) {
                    if (map[nr][nc] == 0) {
                        queue.add(new Node(nr, nc, node.d + 1, node.breakFlag));
                        visit[nr][nc][node.breakFlag] = true;
                    } else if (node.breakFlag == UNBREAK){ //벽인데 아직 안부셔본 경우만
                        queue.add(new Node(nr, nc, node.d + 1, BREAK));
                        visit[nr][nc][BREAK] = true;
                    }
                }
            }
        }
        return -1;
    }

    public static boolean isInBound(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    static class Node {
        public int r;
        public int c;
        public int d;
        public int breakFlag;

        public Node(int r, int c, int d, int breakFlag) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.breakFlag = breakFlag;
        }
    }
}
