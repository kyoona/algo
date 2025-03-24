package org.example;

import java.util.Scanner;

public class Yoona_2018 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int ans = 0;
        int sum = 0;
        int end = n;
        for (int i = n; i > 0; i--) {
            sum += i;
            if (sum == n){
                ans++;
            }
            if(sum >= n){
                sum -= end;
                end--;
            }
        }

        System.out.println(ans);
    }
}
