package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Yoona_18352 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] ins = reader.readLine().split(" ");
        int n = Integer.parseInt(ins[0]);
        int m = Integer.parseInt(ins[1]);
        int k = Integer.parseInt(ins[2]);
        int x = Integer.parseInt(ins[3]);

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            String[] edge = reader.readLine().split(" ");
            graph.get(Integer.parseInt(edge[0])).add(Integer.parseInt(edge[1]));
        }


        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(o1 -> o1.weight));
        boolean[] visit = new boolean[n + 1];

        queue.add(new Node(x, 0));

        List<Integer> ans = new ArrayList<>();
        while (!queue.isEmpty()){
            Node node = queue.poll();
            if(visit[node.n]){
                continue;
            }

            if(node.weight == k){
                ans.add(node.n);
            }
            visit[node.n] = true;


            for(int to : graph.get(node.n)){
                queue.add(new Node(to, node.weight + 1));
            }
        }

        Collections.sort(ans);
        for (Integer i : ans) {
            System.out.println(i);
        }

        if(ans.isEmpty()){
            System.out.println(-1);
        }
    }

    static class Node {
        public int n;
        public int weight;

        public Node(int n, int weight) {
            this.n = n;
            this.weight = weight;
        }
    }
}
