package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Yoona_1948 {

    public static int[] totalTime;
    public static int[] rTotalTime;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(reader.readLine());
        int m = Integer.parseInt(reader.readLine());

        List<List<Edge>> graph = new ArrayList<>();
        List<List<Edge>> rGraph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
            rGraph.add(new ArrayList<>());
        }

        int[] inDegree = new int[n + 1];
        int[] rInDegree = new int[n + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(reader.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Edge(to, time));
            rGraph.get(to).add(new Edge(from, time));
            inDegree[to]++;
            rInDegree[from]++;
        }

        st = new StringTokenizer(reader.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        totalTime = new int[n + 1];
        bfs(start, totalTime, inDegree, graph);
        System.out.println(totalTime[end]);

        rTotalTime = new int[n + 1];
        bfs(end, rTotalTime, rInDegree, rGraph);

        int count = findCount(n, start, end, graph, totalTime, rTotalTime);
        System.out.println(count);
    }

    public static void bfs(int start, int[] totalTime, int[] inDegree, List<List<Edge>> graph){
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            Integer now = queue.poll();

            for (Edge edge : graph.get(now)) {
                inDegree[edge.num]--;
                totalTime[edge.num] = Integer.max(totalTime[edge.num], totalTime[now] + edge.time);

                if(inDegree[edge.num] == 0){
                    queue.add(edge.num);
                }
            }
        }
    }

    public static int findCount(int n, int start, int end, List<List<Edge>> graph, int[] totalTime, int[] rTotalTime){
        boolean[] visit = new boolean[n + 1];
        Queue<Integer> queue = new ArrayDeque<>();

        queue.add(start);
        visit[start] = true;

        int ans = 0;
        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (Edge to : graph.get(now)) {
                if (to.time + totalTime[now] == totalTime[to.num] && rTotalTime[to.num] + to.time == rTotalTime[now]) {
                    if (!visit[to.num]) {
                        visit[to.num] = true;
                        queue.add(to.num);
                    }
                    ans++;
                }
            }
        }

        return ans;
    }

    static class Edge {
        public int num;
        public int time;

        public Edge(int num, int time) {
            this.num = num;
            this.time = time;
        }
    }
}
