package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Yoona_1707 {

    public static List<List<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(reader.readLine());

        graph = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            String[] ins = reader.readLine().split(" ");
            int v = Integer.parseInt(ins[0]);
            int e = Integer.parseInt(ins[1]);

            initGraph(v);

            for (int j = 0; j < e; j++) {
                String[] edge = reader.readLine().split(" ");
                int n1 = Integer.parseInt(edge[0]);
                int n2 = Integer.parseInt(edge[1]);
                graph.get(n1).add(n2);
                graph.get(n2).add(n1);
            }

            String ans = bfs(v) ? "YES" : "NO";
            System.out.println(ans);
        }
    }

    public static boolean bfs(int v){
        boolean[][] visit = new boolean[v + 1][2]; //{방문 여부, 카테고리}
        ArrayDeque<Node> queue = new ArrayDeque();

        for (int i = 1; i < v + 1; i++) {
            if(visit[i][0]){
                continue;
            }

            queue.add(new Node(i, true));
            visit[i][0] = true;
            visit[i][1] = true;
            while (!queue.isEmpty()){
                Node node = queue.poll();

                for (Integer to : graph.get(node.n)) {
                    if(!visit[to][0]){
                        queue.add(new Node(to, !node.category));
                        visit[to][0] = true;
                        visit[to][1] = !node.category;
                    } else if (visit[to][0] && visit[to][1] == node.category) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public static void initGraph(int v){
        graph.clear();
        for (int i = 0; i < v + 1; i++) {
            graph.add(new ArrayList<>());
        }
    }

    static class Node {
        public int n;
        public boolean category;

        public Node(int n, boolean category) {
            this.n = n;
            this.category = category;
        }
    }
}
