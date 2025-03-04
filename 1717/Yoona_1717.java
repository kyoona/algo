package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Yoona_1717 {

    public static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] ins = reader.readLine().split(" ");

        int n = Integer.parseInt(ins[0]);
        int m = Integer.parseInt(ins[1]);

        parent = new int[n + 2];

        for (int i = 0; i < n + 2; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            String[] input = reader.readLine().split(" ");
            int a = Integer.parseInt(input[1]);
            int b = Integer.parseInt(input[2]);

            if(input[0].equals("0")){
                union(a, b);
            } else {
                String ans = findParent(a) == findParent(b) ? "YES" : "NO";
                System.out.println(ans);
            }
        }
    }

    public static void union(int a, int b){
        int parentA = findParent(a);
        int parentB = findParent(b);

        parent[parentA] = parentB;
    }

    public static int findParent(int n){
        if(n == parent[n]){
            return n;
        }

        parent[n] = findParent(parent[n]);
        return parent[n];
    }
}
