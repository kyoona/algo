package org.example;

import java.io.*;
import java.util.*;

public class Yoona_텀프로젝트_9466 {

    static int[] arr;
    static boolean[] visit, fin;
    static int mem;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            arr = new int[n + 1];
            visit = new boolean[n + 1];
            fin = new boolean[n + 1];
            mem = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= n; i++) {
                if (!fin[i]) dfs(i);
            }

            System.out.println(n - mem);
        }
    }

    public static void dfs(int cur) {
        if (fin[cur]) return;

        if (visit[cur]) {
            mem++;
            for (int i = arr[cur]; i != cur; i = arr[i]) {
                mem++;
            }
            fin[cur] = true;
            return;
        }

        visit[cur] = true;
        dfs(arr[cur]);
        fin[cur] = true;
    }
}
