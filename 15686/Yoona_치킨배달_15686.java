package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Yoona_치킨배달_15686 {

    public static int[][] map;
    public static List<List<Node>> possibleChicken;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        List<Node> chicken = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(reader.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2){
                    chicken.add(new Node(i, j));
                }
            }
        }

        //가능한 조합 탐색
        possibleChicken = new ArrayList<>();
        combine(chicken, m, 0, new ArrayList<>());

        //계산
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < possibleChicken.size(); i++) {
            int dis = 0;
            List<Node> chickenCase = possibleChicken.get(i);
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if(map[j][k] != 1) continue;
                    int min = Integer.MAX_VALUE;
                    for (int l = 0; l < chickenCase.size(); l++) {
                        min = Integer.min(
                                Math.abs(j - chickenCase.get(l).x) + Math.abs(k - chickenCase.get(l).y),
                                min
                        );
                    }
                    dis+= min;
                }
            }
            ans = Integer.min(dis, ans);
        }

        System.out.println(ans);
    }

    public static void combine(List<Node> arr, int k, int start, List<Node> select) {
        if(select.size() == k){
            possibleChicken.add(List.copyOf(select));
        }

        for (int i = start; i < arr.size(); i++) {
            select.add(arr.get(i));
            combine(arr, k, i + 1, select);
            select.remove(select.size() - 1);
        }
    }

    static class Node{
        public int x;
        public int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
