package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Yoona_1043 {

    static int parent[];

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(reader.readLine());
        int trueCount = Integer.parseInt(st.nextToken());
        for (int i = 0; i < trueCount; i++) {
            parent[Integer.parseInt(st.nextToken())] = -1; //진실 -1
        }

        boolean[][] party = new boolean[m][n + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(reader.readLine());
            int partyMemCount = Integer.parseInt(st.nextToken());
            int prevMem = -1;
            for (int j = 0; j < partyMemCount; j++) {
                int mem = Integer.parseInt(st.nextToken());
                party[i][mem] = true;
                if(prevMem != -1){
                    union(prevMem, mem);
                }
                prevMem = mem;
            }
        }

        int ans = 0;
        for (int i = 0; i < m; i++) {
            boolean check = true;
            for (int j = 1; j < n + 1; j++) {
                if(party[i][j] && findParent(j) == -1){
                    check = false;
                }
            }
            if (check){
                ans++;
            }
        }

        System.out.println(ans);
    }

    public static void union(int a, int b){
        int parentA = findParent(a);
        int parentB = findParent(b);

        if (parentA < parentB) {
            parent[parentB] = parentA;
        } else if (parentB < parentA){
            parent[parentA] = parentB;
        }
    }

    public static int findParent(int n){
        if(n == parent[n] || parent[n] == -1){
            return parent[n];
        }

        parent[n] = findParent(parent[n]);
        return parent[n];
    }
}
