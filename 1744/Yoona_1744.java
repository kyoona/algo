package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Yoona_1744 {

    public static int[] arr;
    public static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());


        int n = Integer.parseInt(reader.readLine());
        arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(reader.readLine());
        }
        Arrays.sort(arr);

        int index = calPositive(n - 1);
        if(index >= 0){
            calNegative(index);
        }

        System.out.println(ans);
    }

    public static int calPositive(int index){
        if(index == 0){
            ans = arr[0];
            return -1;
        }

        for (int i = index; i > 0; i-=2) {
            if(arr[i] > 1 && arr[i - 1] > 1){
                ans += arr[i] * arr[i - 1];
            } else if (arr[i] >= 1 && arr[i - 1] == 1) {
                ans += arr[i] + arr[i - 1];
            } else if (arr[i] >= 1 && arr[i - 1] <= 0) {
                ans += arr[i];
                return i - 1;
            } else {
                return i;
            }
        }

        if(index % 2 == 0){
            ans += arr[0];
        }
        return -1;
    }

    public static void calNegative(int index){
        if(index == 0){
            ans += arr[index];
            return;
        }

        for (int i = 0; i < index; i+=2) {
            ans += arr[i] * arr[i + 1];
        }

        if(index % 2 == 0){
            ans += arr[index];
        }
    }
}
