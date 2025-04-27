package org.example;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Yoona_게임닉네임_16934 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(reader.readLine());

        Map<String, Integer> nick = new HashMap<>();
        Set<String> prefix = new HashSet<>();
        for (int i = 0; i < n; i++) {
            String name = reader.readLine();
            String alias = "";
            boolean tag = false;
            for (int j = 0; j < name.length(); j++) {
                if(!prefix.contains(name.substring(0, j + 1)) && !tag){
                    alias = name.substring(0, j + 1);
                    tag = true;
                }
                prefix.add(name.substring(0, j + 1));
            }

            if(!tag){
                if (nick.containsKey(name)) {
                    alias = name + (nick.get(name) + 1);
                    prefix.add(alias);
                } else {
                    alias = name;
                }
            }

            nick.put(name, nick.getOrDefault(name, 0) + 1);
            writer.write(alias + "\n");
        }
        writer.flush();
    }
}
