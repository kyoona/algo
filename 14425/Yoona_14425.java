package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Yoona_14425 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        Set<String> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(reader.readLine());
        }

        int ans = 0;
        for (int i = 0; i < m; i++) {
            if(set.contains(reader.readLine())) ans++;
        }

        System.out.println(ans);
    }
}
