package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Yoona_1916 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int m = Integer.parseInt(reader.readLine());

        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(reader.readLine());
            graph.get(Integer.parseInt(st.nextToken())).add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        st = new StringTokenizer(reader.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        boolean[] visit = new boolean[n + 1];
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.wegith));
        queue.add(new Node(start, 0));

        while (!queue.isEmpty()){
            Node node = queue.poll();
            if(visit[node.num]){
                continue;
            }
            visit[node.num] = true;

            if (node.num == end) {
                System.out.println(node.wegith);
                return;
            }

            for (Edge edge : graph.get(node.num)) {
                queue.add(new Node(edge.to, edge.cost + node.wegith));
            }
        }
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
