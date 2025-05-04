package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Yoona_보석도둑_1202 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        PriorityQueue<Jewel> jewels = new PriorityQueue<>((j1, j2) -> j1.m - j2.m);
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(reader.readLine());
            jewels.add(new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int[] bags = new int[k];
        for (int i = 0; i < k; i++) {
            bags[i] = Integer.parseInt(reader.readLine());
        }
        Arrays.sort(bags);

        long ans = 0;
        PriorityQueue<Jewel> possilbe = new PriorityQueue<>((j1, j2) -> j2.v - j1.v);
        for (int i = 0; i < k; i++) {
            while (jewels.size() > 0 && jewels.peek().m <= bags[i]) {
                possilbe.add(jewels.poll());
            }

            if(possilbe.size() > 0){
                ans += possilbe.poll().v;
            }
        }

        System.out.println(ans);
    }

    static class Jewel{
        public int m;
        public int v;

        public Jewel(int m, int v) {
            this.m = m;
            this.v = v;
        }
    }
}
