package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Yoona_1715 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            queue.add(Integer.parseInt(reader.readLine()));
        }

        int ans = 0;
        while (queue.size() > 1){
            int a = queue.poll();
            int b = queue.poll();
            ans += a + b;
            queue.add(a + b);
        }

        System.out.print(ans);
    }
}
