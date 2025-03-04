package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Yoona_1976 {

    public static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        int m = Integer.parseInt(reader.readLine());

        parent = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < n; i++) {
            String[] input = reader.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                if(input[j].equals("1")){
                    union(i + 1, j + 1);
                }
            }
        }

        String[] input = reader.readLine().split(" ");
        int connect = findParent(Integer.parseInt(input[0]));
        for (int i = 1; i < m; i++) {
            if(findParent(Integer.parseInt(input[i])) != connect){
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

    public static void union(int a, int b){
        int parentA = findParent(a);
        int parentB = findParent(b);

        if (parentA <= parentB) {
            parent[parentB] = parentA;
        } else {
            parent[parentA] = parentB;
        }
    }

    public static int findParent(int n){
        if(n == parent[n]){
            return n;
        }

        parent[n] = findParent(parent[n]);

        return parent[n];
    }
}
