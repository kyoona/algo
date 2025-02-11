package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Yoona_13023 {

    public static List<List<Integer>> edges;
    public static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = reader.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int m = Integer.parseInt(firstLine[1]);

        visit = new boolean[n];
        edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }

        for(int i = 0; i < m; i++){
            String[] input = reader.readLine().split(" ");
            int from = Integer.parseInt(input[0]);
            int to = Integer.parseInt(input[1]);
            edges.get(from).add(to);
            edges.get(to).add(from);
        }

        for (int i = 0; i < n; i++) {
            Arrays.fill(visit, false);
            if(dfs(i, 1) >= 5){
                System.out.println(1);
                return;
            }
        }
        System.out.println(0);
    }

    public static int dfs(int x, int depth){
        if(visit[x]){
            return depth - 1;
        }
        visit[x] = true;

        int maxDepth = depth;
        for(Integer to : edges.get(x)){
            maxDepth = Integer.max(maxDepth, dfs(to, depth + 1));
        }
        if(maxDepth < 5){
            visit[x] = false;
        }

        return maxDepth;
    }
}
