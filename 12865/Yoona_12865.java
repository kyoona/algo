package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Yoona_12865 {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = reader.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int k = Integer.parseInt(firstLine[1]);

        Item[] items = new Item[n + 1];
        for (int i = 1; i < n + 1; i++) {
            String[] input = reader.readLine().split(" ");
            items[i] = new Item(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
        }

        int[][] arr = new int[k + 1][n + 1];
        for (int i = 1; i < k + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (items[j].w > i) {
                    arr[i][j] = arr[i][j - 1];
                } else {
                    int indexI = i - items[j].w > 0 ? i - items[j].w : 0;
                    arr[i][j] = Integer.max(arr[i][j - 1], arr[indexI][j - 1] + items[j].v);
                }
            }
        }
        System.out.println(arr[k][n]);
    }

    static class Item {

        public int w;
        public int v;

        public Item(int w, int v) {
            this.w = w;
            this.v = v;
        }
    }
}