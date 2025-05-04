package org.example;

import java.io.*;
import java.util.StringTokenizer;

public class Yoona_트리의순회_2263 {

    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static int n;
    public static int[] preOrder;
    public static int[] inOrder;
    public static int[] postOrder;

    public static int count = 0;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(reader.readLine());

        preOrder = new int[n];
        inOrder = new int[n];
        postOrder = new int[n];

        input(inOrder);
        input(postOrder);

        search(0, n - 1, 0, n - 1);

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < n; i++) {
            writer.write(preOrder[i] + " ");
        }
        writer.flush();
    }

    public static void search(int preL, int preR, int proL, int proR){
        if(preL > preR || preL > preR) return;
        preOrder[count++] = postOrder[proR];

        int preRootIdx = 0;
        for (int i = 0; i < n; i++) {
            if(inOrder[i] == postOrder[proR]){
                preRootIdx = i;
            }
        }

        search(preL, preRootIdx - 1, proL, proL + preRootIdx - preL - 1);
        search(preRootIdx + 1, preR, proL + preRootIdx - preL, proR - 1);
    }

    public static void input(int[] order) throws IOException {
        StringTokenizer st = new StringTokenizer(reader.readLine());

        for (int i = 0; i < n; i++) {
            order[i] = Integer.parseInt(st.nextToken());
        }
    }
}
