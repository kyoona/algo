package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Yoona_문제집_1766 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        int[] indegree = new int[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            indegree[b]++;
        }

        PriorityQueue<Integer> queue = new PriorityQueue();
        for (int i = 1; i < n + 1; i++) {
            if(indegree[i] == 0){
                queue.add(i);
            }
        }

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        while (!queue.isEmpty()){
            Integer cur = queue.poll();
            writer.write(cur + " ");

            for (Integer to : graph.get(cur)) {
                if(--indegree[to] == 0){
                    queue.add(to);
                }
            }
        }

        writer.flush();
    }
}
