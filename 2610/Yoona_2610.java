package org.example;

import java.io.*;
import java.util.*;

public class Yoona_2610 {

    public static int[] parent;
    public static List<List<Integer>> edges;
    public static int maxDist;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int m = Integer.parseInt(reader.readLine());

        parent = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            parent[i] = i;
        }

        StringTokenizer st;
        edges = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            edges.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edges.get(a).add(b);
            edges.get(b).add(a);
            union(a, b);
        }

        Map<Integer, Integer[]> ans = new HashMap<>(); //index, dist
        for (int i = 1; i < n + 1; i++) {
            maxDist = Integer.MIN_VALUE;
            bfs(i, new boolean[n + 1]);

            int parentI = findParent(i);
            Integer[] pre = ans.getOrDefault(parentI, new Integer[]{-1, Integer.MAX_VALUE});
            if(pre[1] > maxDist){
                ans.put(parentI, new Integer[]{i, maxDist});
            }
        }

        System.out.println(ans.size());
        ans.values().stream()
                .map(o -> o[0]).sorted()
                .forEach(System.out::println);
    }

    public static void union(int a, int b){
        int parentA = findParent(a);
        int parentB = findParent(b);
        parent[parentA] = parentB;
    }

    public static int findParent(int n){
        if(parent[n] == n){
            return n;
        }

        return parent[n] = findParent(parent[n]);
    }

    public static void bfs(int n, boolean[] visit){
        Queue<Integer[]> queue = new ArrayDeque<>(); // index, dist
        queue.add(new Integer[]{n, 0});
        visit[n] = true;

        while (!queue.isEmpty()){
            Integer[] now = queue.poll();
            maxDist = Integer.max(maxDist, now[1]);

            for (Integer to : edges.get(now[0])) {
                if(!visit[to]){
                    queue.add(new Integer[]{to, now[1] + 1});
                    visit[to] = true;
                }
            }
        }
    }
}
