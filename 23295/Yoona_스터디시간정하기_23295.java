package org.example;

import java.io.*;
import java.util.*;

public class Yoona_스터디시간정하기_23295 {

    public static final int time  = 100001;
    public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        int[] start = new int[time];
        int[] end = new int[time];

        for (int i = 0; i < n; i++) {
            int k = Integer.parseInt(reader.readLine());
            for (int j = 0; j < k; j++) {
                st = new StringTokenizer(reader.readLine());
                start[Integer.parseInt(st.nextToken())]++;
                end[Integer.parseInt(st.nextToken())]++;
            }
        }

        int[] memCount = new int[time + 1];
        memCount[0] = start[0];
        for (int i = 1; i < time; i++) {
            memCount[i] = memCount[i - 1] + start[i] - end[i];
        }

        int totalTime = 0;
        for (int i = 0; i < t; i++) {
            totalTime += memCount[i];
        }

        int max = totalTime;
        int ans = 0;

        for (int i = t; i < time; i++) {
            totalTime += memCount[i];
            totalTime -= memCount[i - t];

            if (totalTime > max) {
                max = totalTime;
                ans = i - t + 1;
            }
        }


        System.out.println(ans + " " + (ans + t));
    }
}
