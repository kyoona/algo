package org.example;

import java.io.*;
import java.util.*;

public class Yoona_최소비용구하기_11779 {

    public static List<List<Edge>> graph = new ArrayList<>();
    public static int[] prev;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(reader.readLine());
        int m = Integer.parseInt(reader.readLine());

        initGraph(n);
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(reader.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(from).add(new Edge(to, cost));
        }

        st = new StringTokenizer(reader.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        Edge result = dijstra(start, end, n);
        writer.write(result.v + "\n");

        int idx = end;
        List<Integer> path = new ArrayList<>();
        path.add(end);
        while (prev[idx] != -1) {
            path.add(prev[idx]);
            idx = prev[idx];
        }
        writer.write(path.size() + "\n");

        for (int i = path.size() - 1; i >= 0; i--) {
            writer.write(path.get(i) + " ");
        }

        writer.flush();
    }

    public static Edge dijstra(int start, int end, int n) {
        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.v));
        queue.add(new Edge(start, 0));

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        prev = new int[n + 1];
        Arrays.fill(prev, -1);

        while (!queue.isEmpty()) {
            Edge cur = queue.poll();
            if(cur.v > dist[cur.n]) continue;
            if(cur.n == end) return cur;

            for (Edge to : graph.get(cur.n)) {
                int cost = to.v + cur.v;
                if(cost < dist[to.n]) {
                    prev[to.n] = cur.n;
                    dist[to.n] = cost;
                    queue.add(new Edge(to.n, cost));
                }
            }
        }

        return null;
    }

    public static void initGraph(int n) {
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }
    }

    static class Edge {
        public int n;
        public int v;

        public Edge(int n, int v) {
            this.n = n;
            this.v = v;
        }
    }
}
