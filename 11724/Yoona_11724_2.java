package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Yoona_11724_2 {
    public static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = reader.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int m = Integer.parseInt(firstLine[1]);

        parent = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            String[] input = reader.readLine().split(" ");
            int from = Integer.parseInt(input[0]);
            int to = Integer.parseInt(input[1]);
            union(from, to);
        }

        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 1; i < n + 1; i++) {
            hashSet.add(findRoot(i));
        }

        System.out.println(hashSet.size());
    }

    public static void union(int x, int y){
        int rootX = findRoot(x);
        int rootY = findRoot(y);

        parent[rootX] = rootY;
    }

    public static int findRoot(int x){
        if(parent[x] != x){
            parent[x] = findRoot(parent[x]);
        }
        return parent[x];
    }
}
