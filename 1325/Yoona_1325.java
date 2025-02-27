package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Yoona_1325 {

    public static List<List<Integer>> graph = new ArrayList<>();
    public static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] ins = reader.readLine().split(" ");
        int n = Integer.parseInt(ins[0]);
        int m = Integer.parseInt(ins[1]);

        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            String[] edge = reader.readLine().split(" ");
            graph.get(Integer.parseInt(edge[1])).add(Integer.parseInt(edge[0]));
        }

        int max = 0;
        List<Integer> ans = new ArrayList<>();
        visit = new boolean[n + 1];
        for (int i = 1; i < n + 1; i++) {
            Arrays.fill(visit, false);

            int result = dfs(i);
            if(result > max){
                max = result;
                ans.clear();
                ans.add(i);
            } else if (result == max) {
                ans.add(i);
            }
        }

        Collections.sort(ans);
        for (Integer i : ans) {
            System.out.print(i + " ");
        }
    }

    public static int dfs(int n){
        if(visit[n]) {
            return 0;
        }

        int nodeCount = 1;
        visit[n] = true;
        for(int to : graph.get(n)){
            nodeCount += dfs(to);
        }

        return nodeCount;
    }
}
