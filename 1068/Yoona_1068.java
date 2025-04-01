package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Yoona_1068 {

    public static List<List<Integer>> graph = new ArrayList<>();
    public static boolean[] visit;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        StringTokenizer st = new StringTokenizer(scanner.nextLine());
        int root = 0;
        for (int i = 0; i < n; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if(parent == -1) root = i;
            else graph.get(parent).add(i);
        }

        int d = Integer.parseInt(scanner.nextLine());
        visit = new boolean[n];

        System.out.println(dfs(root, d));
    }

    public static int dfs(int n, int d){
        if(visit[n] || n == d) return 0;
        visit[n] = true;

        int child = 0;
        for (Integer to : graph.get(n)) {
            child += dfs(to, d);
        }

        return child == 0 ? 1 : child;
    }
}
