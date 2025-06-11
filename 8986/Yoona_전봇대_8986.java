package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Yoona_전봇대_8986 {

    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static int[] items;
    public static int n;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(reader.readLine());
        StringTokenizer st = new StringTokenizer(reader.readLine());

        items = new int[n];
        for (int i = 0; i < n; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(binarySearch(0, items[items.length - 1]));
    }

    public static long binarySearch(int l, int r){
        while (r - l > 3){
            int m1 = l + (r - l) / 3;
            int m2 = r - (r - l) / 3;

            long result1 = check(m1);
            long result2 = check(m2);

            if (result1 < result2) {
                r = m2;
            } else {
                l = m1;
            }
        }

        long result = Long.MAX_VALUE;
        for (long i = l; i <= r; i++) {
            result = Math.min(result, check(i));
        }

        return result;
    }

    public static long check(long d){
        long total = 0;
        for (int i = 1; i < items.length; i++) { // 0은 고정
            total += Math.abs(items[i] - d * i);
        }

        return total;
    }
}
