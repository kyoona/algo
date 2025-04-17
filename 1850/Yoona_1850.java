package org.example;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Yoona_1850 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");
        long a = Long.parseLong(input[0]);
        long b = Long.parseLong(input[1]);

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        long ans = gcd(a, b);
        for (int i = 0; i < ans; i++) {
            writer.write("1");
        }
        writer.flush();
    }

    public static long gcd(long a, long b){
        if(b == 0){
            return a;
        }
        return gcd(b, a % b);
    }
}
