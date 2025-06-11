package org.example;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Yoona_친구네트워크_4195 {

    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;

    public static Map<String, String> friend = new HashMap<>();
    public static Map<String, Integer> network = new HashMap<>();

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(reader.readLine());

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(reader.readLine());
            friend.clear();
            network.clear();

            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(reader.readLine());
                String mem1 = st.nextToken();
                String mem2 = st.nextToken();

                String root = union(mem1, mem2);
                writer.write(network.get(root) + "\n");
            }
        }

        writer.flush();
    }

    public static String union(String mem1, String mem2){
        String parent1 = findParent(mem1);
        String parent2 = findParent(mem2);

        if(!parent1.equals(parent2)){
            friend.put(parent2, parent1);
            friend.put(parent1, parent1);
            network.put(parent1, network.getOrDefault(parent2, 1) + network.getOrDefault(parent1, 1));
        }

        return parent1;
    }

    public static String findParent(String mem){
        String parent = friend.getOrDefault(mem, mem);
        if(parent.equals(mem)){
            return mem;
        }
        String anc = findParent(parent);
        friend.put(mem, anc);
        return anc;
    }
}
