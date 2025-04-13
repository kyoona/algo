package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Yoona_17472 {

    public static int[][] map;
    public static List<List<Edge>> graph;
    public static int n, m;
    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(reader.readLine());
            for (int j = 0; j < m; j++) {
                if(Integer.parseInt(st.nextToken()) == 1) map[i][j] = 1;
            }
        }

        int mc = setMap(); // 섬 개수

        graph = new ArrayList<>();
        for (int i = 0; i < mc + 1; i++) {
            graph.add(new ArrayList());
        }

        findDist();

        int mst = 0;
        boolean[] visit = new boolean[mc + 1];
        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.w));
        queue.add(new Edge(1, 0));
        while (!queue.isEmpty()){
            Edge cur = queue.poll();
            if(visit[cur.to]) continue;
            visit[cur.to] = true;

            mst += cur.w;

            for (Edge edge : graph.get(cur.to)){
                if(!visit[edge.to]){
                    queue.add(edge);
                }
            }
        }

        for (int i = 1; i < mc + 1; i++) {
            if(!visit[i]) {
                System.out.println(-1);
                return;
            }
        }
        System.out.println(mst);
    }

    public static int setMap(){ //섬 세기
        int count = 0;
        boolean[][] visit = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(!visit[i][j] && map[i][j] > 0){
                    dfs(i, j, visit, ++count);
                }
            }
        }

        return count;
    }

    public static void dfs(int x, int y, boolean[][] visit, int count){
        visit[x][y] = true;
        map[x][y] = count;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(checkBound(nx, ny) && map[nx][ny] > 0 && !visit[nx][ny]){
                dfs(nx, ny, visit, count);
            }
        }
    }

    public static boolean checkBound(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    public static void findDist(){
        //가로 다리 찾기
        for (int i = 0; i < n; i++) {
            boolean hasMap = false;
            int prevIdx = 0;
            for (int j = 0; j < m; j++) {
                if(map[i][j] > 0 && hasMap && j - prevIdx  - 1 > 1){
                    graph.get(map[i][prevIdx]).add(new Edge(map[i][j], j - prevIdx - 1));
                    graph.get(map[i][j]).add(new Edge(map[i][prevIdx], j - prevIdx - 1));
                }
                if (map[i][j] > 0){
                    prevIdx = j;
                    hasMap = true;
                }
            }
        }

        //세로 다리 찾기
        for (int i = 0; i < m; i++) {
            boolean hasMap = false;
            int prevIdx = 0;
            for (int j = 0; j < n; j++) {
                if(map[j][i] > 0 && hasMap && j - prevIdx - 1 > 1){
                    graph.get(map[prevIdx][i]).add(new Edge(map[j][i], j - prevIdx - 1));
                    graph.get(map[j][i]).add(new Edge(map[prevIdx][i], j - prevIdx - 1));
                }
                if (map[j][i] > 0){
                    prevIdx = j;
                    hasMap = true;
                }
            }
        }
    }

    static class Edge {
        public int to;
        public int w;

        public Edge(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }
}
