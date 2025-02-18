package org.example;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Yoona_1920 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(reader.readLine());
        Set<Integer> set = new HashSet<>();

        String[] input1 = reader.readLine().split(" ");
        for(String str : input1){
            set.add(Integer.parseInt(str));
        }

        int m = Integer.parseInt(reader.readLine());
        String[] input2 = reader.readLine().split(" ");
        for(String str : input2){
            String ans = set.contains(Integer.parseInt(str)) ? "1\n" : "0\n";
            writer.write(ans);
        }

        writer.flush();
        writer.close();
        reader.close();
    }
}
