package org.example;

import java.util.*;

public class Yoona_86971 {

    public List<List<Integer>> edges;
    public boolean[] visit;
    public int minDiff;

    public int solution(int n, int[][] wires) {
        minDiff = n;
        edges = new ArrayList<>();
        visit = new boolean[n + 1];

        for(int i = 0; i < n + 1; i++){
            edges.add(new ArrayList());
        }

        for(int i = 0; i < wires.length; i++){
            edges.get(wires[i][0]).add(wires[i][1]);
            edges.get(wires[i][1]).add(wires[i][0]);
        }

        dfs(1, n);

        return minDiff;
    }

    public int dfs(int x, int n){
        if(visit[x]){
            return 0;
        }

        visit[x] = true;

        int otherN = 1;
        for(Integer next : edges.get(x)){
            otherN += dfs(next, n);
        }

        minDiff = Math.min(minDiff, Math.abs(n - otherN -otherN));
        System.out.println(otherN);

        return otherN;
    }
}