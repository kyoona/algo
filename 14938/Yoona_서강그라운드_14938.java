package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Yoona_서강그라운드_14938 {

    public static List<List<Node>> edges = new ArrayList<>();
    public static int[] item;
    public static int[] visit;
    public static int n, m;
    public static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(reader.readLine());
        item = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            item[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n + 1; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(reader.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            edges.get(n1).add(new Node(n2, l));
            edges.get(n2).add(new Node(n1, l));
        }

        visit = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            Arrays.fill(visit, Integer.MAX_VALUE);
            dijstra(i);
            check();
        }

        System.out.println(ans);
    }

    public static void dijstra(int start){
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.v - o2.v);

        queue.add(new Node(start, 0));
        visit[start] = 0;
        while (!queue.isEmpty()){
            Node node = queue.poll();

            for (Node to : edges.get(node.n)) {
                if(visit[to.n] > to.v + node.v){
                    queue.add(new Node(to.n, to.v + node.v));
                    visit[to.n] = to.v + node.v;
                }
            }
        }
    }

    public static void check() {
        int sum = 0;
        for (int i = 1; i < n + 1; i++) {
            if(visit[i] <= m){
                sum += item[i];
            }
        }

        ans = Integer.max(ans, sum);
    }

    static class Node {
        public int n;
        public int v;

        public Node(int n, int v) {
            this.n = n;
            this.v = v;
        }
    }
}
