package org.example;

import java.io.*;
import java.util.StringTokenizer;

public class Yoona_2042 {
    public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        long[] arr = new long[n + 1];
        for (int i = 1; i < n + 1; i++) {
            arr[i] = Long.parseLong(reader.readLine());
        }
        SegmentTree tree = new SegmentTree(arr, n);

        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {
                tree.update(b, c);
            } else {
                writer.write(tree.query(b, (int)c) + "\n");
            }
        }
        writer.flush();
    }

    static class SegmentTree {
        public long[] tree;
        public int n;

        public SegmentTree(long[] arr, int n) {
            this.n = n;
            tree = new long[4 * n];
            build(arr, 0, 1, n);
        }

        private void build(long[] arr, int node, int start, int end) {
            if (start == end) {
                tree[node] = arr[start];
            } else {
                int nl = node * 2 + 1;
                int nr = node * 2 + 2;
                int mid = (start + end) / 2;
                build(arr, nl, start, mid);
                build(arr, nr, mid + 1, end);

                tree[node] = tree[nl] + tree[nr];
            }
        }

        public void update(int idx, long value) {
            update(idx, value, 0, 1, n);
        }

        private void update(int idx, long value, int node, int start, int end) {
            if (start == end) {
                tree[node] = value;
            } else {
                int mid = (start + end) / 2;
                int nl = node * 2 + 1;
                int nr = node * 2 + 2;
                if (idx <= mid) {
                    update(idx, value, nl, start, mid);
                } else {
                    update(idx, value, nr, mid + 1, end);
                }
                tree[node] = tree[nl] + tree[nr];
            }
        }

        public long query(int findS, int findE) {
            return query(0, 1, n, findS, findE);
        }

        private long query(int node, int start, int end, int findS, int findE) {
            if(end < findS || findE < start){
                return 0;
            }
            if(findS <= start && end <= findE) {
                return tree[node];
            }

            int mid = (start + end) / 2;
            int nl = node * 2 + 1;
            int nr = node * 2 + 2;
            return query(nl, start, mid, findS, findE) + query(nr, mid + 1, end, findS, findE);
        }
    }
}
