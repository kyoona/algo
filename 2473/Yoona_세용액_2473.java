package org.example;

import java.io.*;
import java.util.*;

public class Yoona_세용액_2473 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine());
        int[] nums = new int[n];

        StringTokenizer st = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);

        long minAbsSum = Long.MAX_VALUE;
        long a = 0, b = 0, c = 0;

        for (int i = 0; i < n - 2; i++) {
            int l = i + 1;
            int r = n - 1;

            while (l < r) {
                long sum = (long) nums[i] + (long) nums[l] + (long) nums[r];

                if (Math.abs(sum) <= minAbsSum) {
                    minAbsSum = Math.abs(sum);
                    a = nums[i];
                    b = nums[l];
                    c = nums[r];
                }

                if (sum < 0) {
                    l++;
                } else {
                    r--;
                }
            }
        }

        long[] result = new long[]{a, b, c};
        Arrays.sort(result);

        for (long num : result) {
            writer.write(num + " ");
        }
        writer.flush();
    }
}
