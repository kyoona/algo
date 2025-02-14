package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Yoona_1167 {

    public static int v;
    public static List<List<Edge>> edges;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        v = Integer.parseInt(reader.readLine());
        edges = new ArrayList<>();
        for (int i = 0; i < v + 1; i++) {
            edges.add(new ArrayList<>());
        }

        for (int i = 0; i < v; i++) {
            String[] input = reader.readLine().split(" ");
            int n1 = Integer.parseInt(input[0]);
            int edgeNum = (input.length - 2) / 2;
            for (int j = 0; j < edgeNum; j++) {
                edges.get(n1).add(new Edge(Integer.parseInt(input[2 * j + 1]), Integer.parseInt(input[2 * j + 2])));
            }
        }

        Node endNode = dfs(1);
        int ans = dfs(endNode.n).length;
        System.out.print(ans);
    }

    public static Node dfs(int start){
        Deque<Node> queue = new ArrayDeque<>();
        boolean[] visit = new boolean[v + 1];
        queue.add(new Node(start, 0));
        visit[start] = true;

        Node farNode = new Node(0, 0);
        while (!queue.isEmpty()){
            Node node = queue.poll();
            for (Edge edge : edges.get(node.n)){
                if(!visit[edge.to]){
                    Node nextNode = new Node(edge.to, edge.weight + node.length);
                    queue.add(nextNode);
                    visit[edge.to] = true;
                    farNode = nextNode.length > farNode.length ? nextNode : farNode;
                }
            }
        }

        return farNode;
    }

    static class Edge{
        public int to;
        public int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static class Node{
        public int n;
        public int length;

        public Node(int n, int length) {
            this.n = n;
            this.length = length;
        }
    }
}
