package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Yoona_1414 {

    public static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        int totalLen = 0;
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String input = reader.readLine();
            for (int j = 0; j < n; j++) {
                if(input.charAt(j) != '0'){
                    int len = input.charAt(j) - 'a' >= 0 ? input.charAt(j) - 'a' + 1 : input.charAt(j) - 'A' + 27;
                    edges.add(new Edge(i, j, len));
                    totalLen += len;
                }
            }
        }

        Collections.sort(edges, Comparator.comparingInt(e -> e.w));

        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int mst = 0;
        for (Edge edge : edges) {
            if(findParent(edge.from) != findParent(edge.to)){
                union(edge.from, edge.to);
                mst += edge.w;
            }
        }

        for (int i = 1; i < n; i++) {
            if(findParent(i) != findParent(0)){
                System.out.println(-1);
                return;
            }
        }
        System.out.println(totalLen - mst);
    }

    public static void union(int a, int b){
        int parentA = findParent(a);
        int parentB = findParent(b);
        parent[parentB] = parentA;
    }

    public static int findParent(int n){
        if(n == parent[n]){
            return n;
        }
        return parent[n] = findParent(parent[n]);
    }



    static class Edge{

        public int from;
        public int to;
        public int w;

        public Edge(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }
    }
}
