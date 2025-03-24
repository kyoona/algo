package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Yoona_14501 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        StringTokenizer st;
        int[] day = new int[n + 1];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(reader.readLine());
            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            if(i + t > n){
                continue;
            }

            int index = i + t;
            while (index < n + 1){
                day[index] = Integer.max(day[i] + p, day[index]);
                index++;
            }
        }

        System.out.println(day[n]);
    }
}
