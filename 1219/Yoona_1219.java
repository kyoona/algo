package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Long.MIN_VALUE;

public class Yoona_1219 {

    public static List<List<Bus>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());

        int n = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(reader.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(from).add(new Bus(to, cost));
        }

        st = new StringTokenizer(reader.readLine());
        Long[] cityMoney = new Long[n];
        for (int i = 0; i < n; i++) {
            cityMoney[i] = Long.parseLong(st.nextToken());
        }

        String ans = bellmanFord(n, start, end, cityMoney);
        System.out.println(ans);
    }

    public static String bellmanFord(int n, int start, int end, Long[] cityMoney) {
        Long[] money = new Long[n];
        Arrays.fill(money, MIN_VALUE);
        money[start] = cityMoney[start];

        for (int i = 0; i < n - 1; i++) {
            for (int from = 0; from < n; from++) {
                for (Bus bus : graph.get(from)) {
                    if(money[from] != MIN_VALUE && money[bus.to] < money[from] + cityMoney[bus.to] - bus.cost) {
                        money[bus.to] = money[from] + cityMoney[bus.to] - bus.cost;
                    }
                }
            }
        }

        if(money[end] == MIN_VALUE){
            return "gg";
        }

        boolean[] visit = new boolean[n];
        for (int from = 0; from < n; from++) {
            for (Bus bus : graph.get(from)) {
                if (money[from] != MIN_VALUE && money[bus.to] < money[from] + cityMoney[bus.to] - bus.cost) {
                    Arrays.fill(visit, false);
                    if (dfs(bus.to, end, visit)){
                        return "Gee";
                    }
                }
            }
        }

        return String.valueOf(money[end]);
    }

    public static boolean dfs(int n, int end, boolean[] visit) {
        if(visit[n]){
            return false;
        }
        visit[n] = true;

        if(n == end){
            return true;
        }

        for (Bus bus : graph.get(n)) {
            if(dfs(bus.to, end, visit)){
                return true;
            }
        }

        return false;
    }

    static class Bus {
        public int to;
        public int cost;

        public Bus(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
}
