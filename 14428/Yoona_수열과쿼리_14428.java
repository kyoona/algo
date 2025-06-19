package org.example;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Yoona_수열과쿼리_14428 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(reader.readLine());
        int[] arr = new int[n + 1]; //끝 값은 사용하지 않는 값
        arr[n] = Integer.MAX_VALUE;

        st = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        SegmentTree tree = new SegmentTree(n, arr);

        int m = Integer.parseInt(reader.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(reader.readLine());
            if (st.nextToken().equals("1")) {
                int idx = Integer.parseInt(st.nextToken()) - 1;
                int v = Integer.parseInt(st.nextToken());
                tree.update(idx, v);
            } else {
                int start = Integer.parseInt(st.nextToken()) - 1;
                int end = Integer.parseInt(st.nextToken()) - 1;
                writer.write(tree.query(start, end) + 1 + "\n");
            }
        }

        writer.flush();
    }

    static class SegmentTree {

        public int[] tree;
        public int[] arr;
        public int n;

        public SegmentTree(int n, int[] arr) {
            this.tree = new int[n * 4 + 1];
            this.arr = arr;
            this.n = n;

            build();
        }

        public void build() {
            Arrays.fill(tree, n);
            build(0, 0, n - 1);
        }

        public int build(int node, int l, int r) {
            if(l >= r) return tree[node] = l; //인덱스를 저장

            int idxL = build(node * 2 + 1, l, (l + r) / 2);
            int idxR = build(node * 2 + 2, (l + r) / 2 + 1, r);
            return tree[node] = arr[idxL] <= arr[idxR] ? idxL : idxR; //작은 값의 인덱스를 저장
        }

        public int query(int start, int end){
            return query(start, end, 0, 0, n - 1);
        }

        public int query(int start, int end, int node, int l, int r) {
            if(start <= l && r <= end) return tree[node];
            if(end < l || r < start || l >= r) return n;

            int idxL = query(start, end, node * 2 + 1, l, (l + r) / 2);
            int idxR = query(start, end, node * 2 + 2, (l + r) / 2 + 1, r);
            return arr[idxL] <= arr[idxR] ? idxL : idxR;
        }

        public void update(int i, int v) {
            update(i, v, 0, 0, n - 1);
        }

        public void update(int i, int v, int node, int l, int r) {
            if(l >= r) {
                arr[i] = v;
                return;
            }

            int nl = node * 2 + 1;
            int nr = node * 2 + 2;
            int m = (l + r) / 2;
            if(i <= m) update(i, v, nl, l, m);
            else update(i, v, nr, m + 1, r);

            tree[node] = arr[tree[nl]] <= arr[tree[nr]] ? tree[nl] : tree[nr];
        }
    }
}
