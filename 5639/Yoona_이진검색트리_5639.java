package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Yoona_이진검색트리_5639 {

    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static List<Integer> preOrder = new ArrayList<>();
    public static int[] postOrder;

    public static void main(String[] args) throws IOException {

        String input;
        while ((input = reader.readLine()) != null && !input.isEmpty()){
            preOrder.add(Integer.parseInt(input));
        }

        postOrder = new int[preOrder.size()];

        search(0, preOrder.size() - 1);

        writer.flush();
    }

    public static void search(int l, int r) throws IOException {
        if(l > r){
            return;
        }

        int idx = l + 1;
        while (idx <= r && preOrder.get(idx) < preOrder.get(l)){
            idx++;
        }

        search(l + 1, idx - 1);
        search(idx, r);

        writer.write(preOrder.get(l) + "\n");
    }
}
