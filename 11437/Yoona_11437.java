package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Yoona_11437 {

    public static List<List<Integer>> tree;
    public static int[][] parents;
    public static int[] depth;
    public static int h = 20;

    public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(reader.readLine());
        parents = new int[h][n + 1];
        depth = new int[n + 1];

        tree = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(reader.readLine());
            int i1 = Integer.parseInt(st.nextToken());
            int i2 = Integer.parseInt(st.nextToken());
            tree.get(i1).add(i2);
            tree.get(i2).add(i1);
        }


        initParents(1, 0);
        fillParents(n);

        int m = Integer.parseInt(reader.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            writer.write(lca(a, b) + "\n");
        }

        writer.flush();
    }

    public static void initParents(int cur, int parent) {
        parents[0][cur] = parent;

        for (Integer to : tree.get(cur)) {
            if(to != parents[0][cur]){
                depth[to] = depth[cur] + 1;
                initParents(to, cur);
            }
        }
    }

    public static void fillParents(int n){
        for (int i = 1; i < h; i++) {
            for (int j = 1; j < n + 1; j++) {
                parents[i][j] = parents[i - 1][parents[i - 1][j]];
            }
        }
    }

    public static int lca(int a, int b){
        if(depth[a] < depth[b]){
            int tmp = a;
            a = b;
            b = tmp;
        }

        for (int i = h - 1; i >= 0; i--) {
            if(depth[a] - (1 << i) >= depth[b]){
                a = parents[i][a];
            }
        }

        if (a == b) return a;

        for (int i = h - 1; i >= 0; i--) {
            if(parents[i][a] != parents[i][b]){
                a = parents[i][a];
                b = parents[i][b];
            }
        }

        return parents[0][a];
    }
}
