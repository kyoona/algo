package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Yoona_환승_5214 {

    public static List<Integer>[] graph;
    public static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + m + 1];
        for (int i = 0; i < n + m + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(reader.readLine());
            int tube = n + i + 1;
            for (int j = 0; j < k; j++) {
                int node = Integer.parseInt(st.nextToken());
                graph[node].add(tube);
                graph[tube].add(node);
            }
        }

        visit = new boolean[n + m + 1];
        System.out.println(bfs(n));
    }

    public static int bfs(int end) {
        Queue<Node> queue = new ArrayDeque(); //node, count
        queue.add(new Node(1, 1));

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if(node.num == end){
                return node.w;
            }

            for (Integer to : graph[node.num]) {
                if (!visit[to]) {
                    visit[to] = true;
                    if (to > end) {
                        queue.add(new Node(to, node.w));
                    } else {
                        queue.add(new Node(to, node.w + 1));
                    }
                }
            }
        }

        return -1;
    }

    static class Node{
        public int num;
        public int w;

        public Node(int num, int w) {
            this.num = num;
            this.w = w;
        }
    }
}
