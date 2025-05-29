package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Yoona_냅색문제_1450 {
    
    public static int[] item;
    public static int n;
    public static int c;
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());

        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(reader.readLine());
        item = new int[n];
        for (int i = 0; i < n; i++) {
            item[i] = Integer.parseInt(st.nextToken());
        }

        List<Long> aSubset = new ArrayList<>();
        getSum(-1, 0, n / 2, aSubset);
        Collections.sort(aSubset, Collections.reverseOrder());

        List<Long> bSubset = new ArrayList<>();
        getSum(n / 2, 0, n - 1, bSubset);
        Collections.sort(bSubset);

        int ans = 0;
        int aIdx = 0, bIdx = 0;
        while (aIdx < aSubset.size()) {
            long k = c - aSubset.get(aIdx);
            while (bIdx < bSubset.size() && bSubset.get(bIdx) <= k){
                bIdx++;
            }

            ans += bIdx;
            aIdx++;
        }

        System.out.println(ans);
    }

    public static void getSum(int idx, long sum, int r, List<Long> subSet){
        if(sum > c){
            return;
        }
        subSet.add(sum);

        for (int i = idx + 1; i <= r; i++) {
            getSum(i, sum + item[i], r, subSet);
        }
    }
}
