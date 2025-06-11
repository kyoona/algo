package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Yoona_중량제한_1939 {

    public static List<List<Edge>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());

        int n = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        int m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            graph.get(a).add(new Edge(b, c));
            graph.get(b).add(new Edge(a, c));
        }

        st = new StringTokenizer(reader.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        System.out.println(dijstra(start, end, n));
    }

    public static long dijstra(int start, int end, int n){
        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingLong((Edge o) -> o.limit).reversed());
        queue.add(new Edge(start, Integer.MAX_VALUE));

        boolean[] visit = new boolean[n + 1];
        while (!queue.isEmpty()) {
            Edge cur = queue.poll();
            if(cur.n == end){
                return cur.limit;
            }
            visit[cur.n] = true;

            for (Edge to : graph.get(cur.n)) {
                if(!visit[to.n]){
                    queue.add(new Edge(to.n, Long.min(to.limit, cur.limit)));
                }
            }
        }

        return -1;
    }

    static class Edge {
        public int n;
        public long limit;

        public Edge(int n, long limit) {
            this.n = n;
            this.limit = limit;
        }
    }
}
