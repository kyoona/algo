package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Yoona_1197 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < v + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Edge(b, c));
            graph.get(b).add(new Edge(a, c));
        }

        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.w));
        queue.add(new Edge(1, 0));

        long mst = 0;
        boolean[] visit = new boolean[v + 1];
        while (!queue.isEmpty()){
            Edge cur = queue.poll();
            if(visit[cur.to]) continue;
            visit[cur.to] = true;

            mst += cur.w;

            for( Edge edge : graph.get(cur.to)){
                if(!visit[edge.to]){
                    queue.add(edge);
                }
            }
        }

        System.out.println(mst);
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
