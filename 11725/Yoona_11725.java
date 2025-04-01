package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Yoona_11725 {

    public static int[] parents;
    public static List<List<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        StringTokenizer st;
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(reader.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            graph.get(n1).add(n2);
            graph.get(n2).add(n1);
        }

        parents = new int[n + 1];
        dfs(1, 1);

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 2; i < n + 1; i++) {
            writer.write(parents[i] + "\n");
        }
        writer.flush();
    }

    public static void dfs(int n, int parent){
        if (parents[n] > 0 ) return;
        parents[n] = parent;

        for (Integer to : graph.get(n)) {
            dfs(to, n);
        }
    }

    static class Node {
        public int n;
        public int depth;

        public Node(int n, int depth) {
            this.n = n;
            this.depth = depth;
        }
    }
}
