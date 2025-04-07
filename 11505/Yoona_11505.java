package org.example;

import java.io.*;
import java.util.StringTokenizer;

public class Yoona_11505 {
    public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(reader.readLine());
        }

        SegmentTree tree = new SegmentTree(n, arr);
        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == 1) {
                tree.update(b - 1, c);
            } else {
                writer.write(tree.query(b - 1, c - 1) + "\n");
            }
        }

        writer.flush();
    }

    static class SegmentTree {
        public int n;
        public long[] tree;

        public SegmentTree(int n, int[] arr) {
            this.n = n;
            tree = new long[4 * n];

            build(arr, 0, 0, n - 1);
        }

        private void build(int[] arr) {
            build(arr, 0, 0, n - 1);
        }

        private void build(int[] arr, int node, int l, int r) {
            if(l == r) {
                tree[node] = arr[l];
                return;
            }

            int mid = (l + r) / 2;
            int lc = node * 2 + 1;
            int rc = node * 2 + 2;
            build(arr, lc, l, mid);
            build(arr, rc, mid + 1, r);
            tree[node] = (tree[lc] * tree[rc]) % 1000000007;
        }

        public long query(int start, int end) {
            return query(0, 0, n - 1, start, end);
        }

        private long query(int node, int l, int r, int start, int end){
            if(r < start || l > end) return 1;
            if (start <= l && r <= end) return tree[node];

            int mid = (l + r) / 2;
            int lc = node * 2 + 1;
            int rc = node * 2 + 2;
            return (query(lc, l, mid, start, end) * query(rc, mid + 1, r, start, end)) % 1000000007;
        }

        public void update(int idx, int v){
            update(idx, v, 0, 0, n - 1);
        }

        public void update(int idx, int v, int node, int l, int r){
            if(l == r) {
                tree[node] = v;
                return;
            }

            int mid = (l + r) / 2;
            int lc = node * 2 + 1;
            int rc = node * 2 + 2;
            if(idx <= mid) update(idx, v, lc, l, mid);
            else update(idx, v, rc, mid + 1, r);

            tree[node] = (tree[lc] * tree[rc]) % 1000000007;
        }
    }
}
