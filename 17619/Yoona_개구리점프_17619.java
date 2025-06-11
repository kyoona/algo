package org.example;

import java.io.*;
import java.util.*;

public class Yoona_개구리점프_17619 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(reader.readLine());

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        List<Tree> treeList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(reader.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            treeList.add(new Tree(x1, x2, i + 1));
        }

        Collections.sort(treeList, Comparator.comparingInt(t -> t.x1)); //x1좌표가 빠른 순서대로
        int[] group = new int[n + 1];
        int uuid = 1;
        int prevX2 = -1;
        for (Tree tree : treeList) {
            if(tree.x1 > prevX2) {
                uuid++;
            }
            group[tree.num] = uuid;
            prevX2 = Math.max(prevX2, tree.x2);
        }

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(reader.readLine());
            int t1 = Integer.parseInt(st.nextToken());
            int t2 = Integer.parseInt(st.nextToken());
            writer.write(group[t1] == group[t2] ? "1\n" : "0\n");
        }

        writer.flush();
    }

    static class Tree {
        public int x1;
        public int x2;
        public int num;

        public Tree(int x1, int x2, int num) {
            this.x1 = x1;
            this.x2 = x2;
            this.num = num;
        }
    }
}
