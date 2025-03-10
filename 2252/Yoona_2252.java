package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Yoona_2252 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        int[] degree = new int[n + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(reader.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph.get(from).add(to);
            degree[to]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i < n + 1; i++) {
            if(degree[i] == 0){
                queue.add(i);
            }
        }

        List<Integer> answer = new ArrayList<>();
        while (!queue.isEmpty()){
            Integer node = queue.poll();
            answer.add(node);

            for(int to : graph.get(node)){
                degree[to]--;
                if(degree[to] == 0){
                    queue.add(to);
                }
            }
        }

        for (Integer i : answer) {
            System.out.print(i + " ");
        }
    }
}
