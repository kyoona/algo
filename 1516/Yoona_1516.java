package org.example;

import java.io.*;
import java.util.*;

public class Yoona_1516 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(reader.readLine());

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        int[] inDegree = new int[n + 1];
        int[] times = new int[n + 1];
        int[] totalTime = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(reader.readLine());
            times[i] = Integer.parseInt(st.nextToken());

            int pre;
            while ((pre = Integer.parseInt(st.nextToken())) != -1){
                    inDegree[i]++;
                    graph.get(pre).add(i);
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i < n + 1; i++) {
            if(inDegree[i] == 0) {
                queue.add(i);
                totalTime[i] = times[i];
            }
        }

        while (!queue.isEmpty()){
            Integer now = queue.poll();

            for(Integer to : graph.get(now)){
                inDegree[to]--;
                totalTime[to] = Integer.max(totalTime[to], totalTime[now] + times[to]);
                if(inDegree[to] == 0){
                    queue.add(to);
                }
            }
        }

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 1; i < n + 1; i++) {
            writer.write(totalTime[i] + "\n");
        }
        writer.flush();
        reader.close();
        writer.close();
    }
}
