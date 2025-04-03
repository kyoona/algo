package org.example;

import java.io.*;
import java.util.StringTokenizer;

public class Yoona_10868 {
    public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(reader.readLine());
        }

        SegmentTree tree = new SegmentTree(n, arr);
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            writer.write(tree.query(a - 1, b - 1) + "\n");
        }

        writer.flush();
    }

    static class SegmentTree {
        public int n;
        public int[] tree;

        public SegmentTree(int n, int[] arr) {
            this.n = n;
            tree = new int[4 * n];

            build(arr, 0, 0, n - 1);
        }

        private void build(int[] arr, int node, int start, int end){
            if (start == end) {
                tree[node] = arr[start];
            } else {
                int mid = (start + end) / 2;
                int l = 2 * node + 1;
                int r = 2 * node + 2;
                build(arr, l, start, mid);
                build(arr, r, mid + 1, end);

                tree[node] = Integer.min(tree[l], tree[r]);
            }
        }

        private int query(int findS, int findE){
            return query(0, 0, n - 1, findS, findE);
        }

        public int query(int node, int start, int end, int findS, int findE){
            if(findS > end || findE < start){
                return Integer.MAX_VALUE;
            }
            if(findS <= start && end <= findE){
                return tree[node];
            }

            int mid = (start + end) / 2;
            int l = 2 * node + 1;
            int r = 2 * node + 2;

            int lMin = query(l, start, mid, findS, findE);
            int rMin = query(r, mid + 1, end, findS, findE);
            return Integer.min(lMin, rMin);
        }
    }
}
