package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Juyeon_13023 {

    static ArrayList<ArrayList<Integer>> arr;
    static boolean[] visited;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            arr.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr.get(a).add(b);
            arr.get(b).add(a);
        }

        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            if (answer != 1) {
                DFS(i, 1);
            }
        }

        bw.write(answer + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    private static void DFS(int e, int depth) {
        if (depth == 5) {
            answer = 1;
            return;
        }

        visited[e] = true;

        for (int i : arr.get(e)) {
            if (!visited[i]) {
                DFS(i, depth + 1);
            }
        }

        visited[e] = false;
    }
}
