package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Yoona_골목길_1738 {

    public static List<List<Edge>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(reader.readLine());
            graph.get(Integer.parseInt(st.nextToken())).add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int[] parent = new int[n + 1];
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MIN_VALUE);

        parent[1] = 1;
        dist[1] = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int from = 1; from < graph.size(); from++) {
                for (Edge edge : graph.get(from)) {
                    if(dist[from] != Integer.MIN_VALUE && dist[edge.to] < dist[from] + edge.w){
                        parent[edge.to] = from;
                        dist[edge.to] = dist[from] + edge.w;
                    }
                }
            }
        }

        for (int from = 1; from < graph.size(); from++) {
            for (Edge edge : graph.get(from)) {
                if(dist[from] != Integer.MIN_VALUE && dist[edge.to] < dist[from] + edge.w){
                    if(dfs(edge.to, n, new boolean[n + 1])){
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }

        int index = n;
        List<Integer> path = new ArrayList<>();
        while (index != 1){
            path.add(index);
            index = parent[index];
        }
        path.add(1);

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = path.size() - 1; i >= 0; i--) {
            writer.write(path.get(i) + " ");
        }
        writer.flush();
    }

    public static boolean dfs(int n, int end, boolean[] visit){
        if(visit[n]) {
            return false;
        }
        if(n == end){
            return true;
        }

        visit[n] = true;
        for (Edge edge : graph.get(n)) {
            if(dfs(edge.to, end, visit)){
                return true;
            }
        }

        return false;
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
