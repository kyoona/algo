package org.example;

import java.util.Scanner;
import java.util.StringTokenizer;

// 4 5 6 1 2 3 4 5
public class Yoona_LIS_12015 {

    public static int n;
    public static int[] arr;
    public static int[] last;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        n = Integer.parseInt(scanner.nextLine());
        arr = new int[n];
        last = new int[n + 1];

        StringTokenizer st = new StringTokenizer(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int len = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] > last[len]) {
                last[++len] = arr[i];
            } else {
                int idx = search(0, len, arr[i]);
                if(idx >= 0){
                    last[idx] = arr[i];
                }
            }
        }

        System.out.println(len);
    }

    public static int search(int l, int r, int v){
        while (l < r) {
            int mid = (l + r) / 2;
            if (last[mid] < v) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return r;
    }

}

//https://loosie.tistory.com/376