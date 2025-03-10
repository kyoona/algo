package org.example;

import java.io.*;
import java.util.*;

public class Yoona_1854 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] visit = new int[n + 1][2]; // count, weight
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
            visit[i][0] = 0;
            visit[i][1] = -1;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(reader.readLine());
            graph.get(Integer.parseInt(st.nextToken())).add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.wegith));
        queue.add(new Node(1, 0));

        while (!queue.isEmpty()){
            Node node = queue.poll();
            if(visit[node.num][0] >= k){
                continue;
            }

            visit[node.num][0]++;
            visit[node.num][1] = node.wegith;

            for(Edge edge : graph.get(node.num)){
                queue.add(new Node(edge.to, edge.cost + node.wegith));
            }
        }

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 1; i < n + 1; i++) {
            int ans = visit[i][0] == k ? visit[i][1] : -1;
            writer.write(ans + "\n");
        }
        writer.flush();
    }

    static class Edge {
        public int to;
        public int cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static class Node {
        public int num;
        public int wegith;

        public Node(int num, int wegith) {
            this.num = num;
            this.wegith = wegith;
        }
    }
}
