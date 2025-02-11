package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Yoona_11724 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = reader.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int m = Integer.parseInt(firstLine[1]);

        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            edges.add(new ArrayList<>());
        }
        
        for (int i = 0; i < m; i++) {
            String[] input = reader.readLine().split(" ");
            int from = Integer.parseInt(input[0]);
            int to = Integer.parseInt(input[1]);
            edges.get(from).add(to);
            edges.get(to).add(from);
        }

        boolean[] visit = new boolean[n + 1];
        Stack<Integer> stack = new Stack();
        int ans = 0;

        for (int i = 1; i < n + 1; i++) {
            if(!visit[i]){
                stack.push(i);
                ans++;
            }

            while(!stack.isEmpty()){
                Integer node = stack.pop();
                if(visit[node]){
                    continue;
                }
                visit[node] = true;

                for(Integer to : edges.get(node)){
                    stack.push(to);
                }
            }
        }

        System.out.println(ans);
    }
}
