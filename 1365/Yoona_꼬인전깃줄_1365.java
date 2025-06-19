package org.example;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Yoona_꼬인전깃줄_1365 {

    public static int n;
    public static int[] arr;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = Integer.parseInt(scanner.nextLine());
        arr = new int[n + 1]; //arr[i] = i길이의 수열에서 가장 큰 수
        int len = 0;

        StringTokenizer st = new StringTokenizer(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num >= arr[len]) { //
                arr[++len] = num;
            } else {
                int idx = findUpdateIdx(num, len);
                arr[idx] = num;
            }
        }

        System.out.println(n - len);
    }

    public static int findUpdateIdx(int num, int len){
        int l = 0;
        int r = len;
        while (l < r) {
            int m = (l + r) / 2;
            if (num < arr[m]) r = m;
            else l = m + 1;
        }
        return l;
    }
}
