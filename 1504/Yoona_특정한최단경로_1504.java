package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Yoona_특정한최단경로_1504 {

    public static List<List<Integer[]>> graph = new ArrayList<>();
    public static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());

        n = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Integer[]{b, c}); //양방향 간선 추가
            graph.get(b).add(new Integer[]{a, c});
        }

        st = new StringTokenizer(reader.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        long path = dijstra(v1, v2);
        long path1 = dijstra(v1, n) + dijstra(v2, 1);
        long path2 = dijstra(v1, 1) + dijstra(v2, n);

        if(path < 0 || (path1 < 0 && path2 < 0)){
            System.out.println(-1);
        } else if (path1 < 0) {
            System.out.println(path2 + path);
        } else if (path2 < 0) {
            System.out.println(path1 + path);
        } else {
            System.out.println(Long.min(path1, path2) + path);
        }


    }

    public static int dijstra(int start, int end) {
        if (start == end) {
            return 0;
        }

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Integer[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        queue.add(new Integer[]{start, 0});
        dist[start] = 0;

        while (!queue.isEmpty()) {
            Integer[] node = queue.poll();

            if (node[0] == end) {
                return node[1];
            }

            for (Integer[] to : graph.get(node[0])) {
                int toDist = node[1] + to[1];
                if (toDist < dist[to[0]]) {
                    queue.add(new Integer[]{to[0], toDist});
                    dist[to[0]] = toDist;
                }
            }
        }

        return Integer.MIN_VALUE;
    }
}
