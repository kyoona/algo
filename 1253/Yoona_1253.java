package org.example;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Yoona_1253 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        StringTokenizer st = new StringTokenizer(scanner.nextLine());
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);

        int ans = 0;
        for (int i = 0; i < n; i++) {
            int f = 0, e = n - 1;
            while (f < e){
                int sum = nums[f] + nums[e];
                if(f == i) f++;
                else if (e == i) e--;
                else if(sum > nums[i]) e--;
                else if (sum < nums[i]) f++;
                else {
                    ans++;
                    break;
                }
            }
        }

        System.out.println(ans);
    }
}
