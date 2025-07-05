package org.example;

import java.io.*;
import java.util.*;

public class Yoona_책나눠주기_9576 {

    public static int M, N;
    public static List<Integer>[] want;
    static int[] bookOwner;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(reader.readLine());

        while (T-- > 0) {
            st = new StringTokenizer(reader.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            want = new List[M + 1];
            bookOwner = new int[N + 1];
            for (int i = 1; i < M + 1; i++) {
                want[i] = new ArrayList<>();
            }

            for (int i = 1; i < M + 1; i++) {
                st = new StringTokenizer(reader.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                for (int j = a; j <= b; j++) {
                    want[i].add(j); //i번째 사람이 j를 원함
                }
            }

            int ans = 0;
            for (int i = 1; i < M + 1; i++) { //사람마다 책 배정
                boolean[] visit = new boolean[N + 1];
                if(dfs(i, visit)){
                    ans++;
                }
            }
            writer.write(ans + "\n");
        }
        writer.flush();
    }
    
    public static boolean dfs(int student, boolean[] visit) {
        for (Integer book : want[student]) {
            if(visit[book]) continue;
            visit[book] = true;
            if (bookOwner[book] == 0 || dfs(bookOwner[book], visit)) {
                bookOwner[book] = student;
                return true;
            }
        }
        return false;
    }
}
