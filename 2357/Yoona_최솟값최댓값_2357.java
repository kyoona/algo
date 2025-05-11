package org.example;

import java.io.*;
import java.util.StringTokenizer;

public class Yoona_최솟값최댓값_2357 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] num = new int[n];
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(reader.readLine());
        }

        SegmentTree tree = new SegmentTree(n, num);

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(reader.readLine());
            int i1 = Integer.parseInt(st.nextToken());
            int i2 = Integer.parseInt(st.nextToken());

            int[] result = tree.query(i1 - 1, i2 - 1);
            writer.write(result[0] + " " + result[1] + "\n");
        }

        writer.flush();
    }

    static class SegmentTree {
        public int n;
        public int[] minTree;
        public int[] maxTree;

        public SegmentTree(int n, int[] arr) {
            this.n = n;
            minTree = new int[4 * n];
            maxTree = new int[4 * n];

            build(arr);
        }

        public void build(int[] arr){
            build(arr, 1, 0, n - 1);
        }

        public int[] build(int[] arr, int node, int l, int r){ //{min, max}
            if(l == r){
                minTree[node] = arr[l];
                maxTree[node] = arr[l];
                return new int[]{arr[l], arr[l]};
            }

            int m = (l + r) /2;
            int[] i1 = build(arr, node * 2, l, m);
            int[] i2 = build(arr, node * 2 + 1, m + 1, r);

            int min = Integer.min(i1[0], i2[0]);
            int max = Integer.max(i1[1], i2[1]);

            minTree[node] = min;
            maxTree[node] = max;

            return new int[]{min, max};
        }

        public int[] query(int s, int e){
            return query(1, s, e, 0, n - 1);
        }

        public int[] query(int node, int s, int e, int l, int r) {
            if (r < s || e < l) {
                return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};
            }

            if (s <= l && r <= e) {
                return new int[]{minTree[node], maxTree[node]};
            }

            int m = (l + r) / 2;
            int[] i1 = query(node * 2, s, e, l, m);
            int[] i2 = query(node * 2 + 1, s, e, m + 1, r);

            int min = Integer.min(i1[0], i2[0]);
            int max = Integer.max(i1[1], i2[1]);

            return new int[]{min, max};
        }
    }
}
