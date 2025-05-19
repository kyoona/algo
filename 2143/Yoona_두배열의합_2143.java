package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Yoona_두배열의합_2143 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(reader.readLine());

        int n = Integer.parseInt(reader.readLine());
        int[] a = new int[n];
        StringTokenizer st = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(reader.readLine());
        int[] b = new int[m];
        st = new StringTokenizer(reader.readLine());
        for (int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        List<Long> aSum = calSum(n, a);
        List<Long> bSum = calSum(m, b);

        Collections.sort(aSum);
        Collections.sort(bSum, Comparator.reverseOrder());

        int aIdx = 0, bIdx = 0;
        long answer = 0;
        while (aIdx < aSum.size() && bIdx < bSum.size()) {
            long sum = aSum.get(aIdx) + bSum.get(bIdx);
            if(sum == t){
                long aVal = aSum.get(aIdx);
                long bVal = bSum.get(bIdx);

                long aCount = 0;
                while (aIdx < aSum.size() && aSum.get(aIdx) == aVal) {
                    aCount++;
                    aIdx++;
                }

                long bCount = 0;
                while (bIdx < bSum.size() && bSum.get(bIdx) == bVal) {
                    bCount++;
                    bIdx++;
                }

                answer += bCount * aCount;
            } else if(sum < t) {
                aIdx++;
            } else if (sum > t) {
                bIdx++;
            }
        }

        System.out.println(answer);
    }
    
    public static List<Long> calSum(int n, int[] arr){
        List<Long> cumSum = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            long sum = 0;
            for (int j = i; j < n; j++) {
                sum += arr[j];
                cumSum.add(sum);
            }
        }

        return cumSum;
    }
}
