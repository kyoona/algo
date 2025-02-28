package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.SortedSet;
import java.util.TreeSet;

import static java.lang.Math.min;

public class Yoona_2251 {

    public static boolean[][][] visit;
    public static int a;
    public static int b;
    public static int c;
    public static SortedSet<Integer> set = new TreeSet();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] ins = reader.readLine().split(" ");

        a = Integer.parseInt(ins[0]);
        b = Integer.parseInt(ins[1]);
        c = Integer.parseInt(ins[2]);
        visit = new boolean[a + 1][b + 1][c + 1];

        dfs(0, 0, c);
        for (Integer i : set) {
            System.out.print(i + " ");
        }
    }

    public static void dfs(int ax, int bx, int cx){
        if(visit[ax][bx][cx]){
            return;
        }

        if(ax == 0){
            set.add(cx);
        }

        visit[ax][bx][cx] = true;
        int[][] dx = calDx(ax, bx, cx); //(a, b, c),(da, db, dc)
        for (int i = 0; i < 6; i++) {
            dfs(ax + dx[0][i], bx + dx[1][i], cx + dx[2][i]);
        }
    }

    public static int[][] calDx(int ax, int bx, int cx) {
        int remainA = a - ax;
        int remainB = b - bx;
        int remainC = c - cx;

        int[][] dx = {
                {-min(ax, remainC), -min(ax, remainB), min(bx, remainA), 0, 0, min(cx, remainA)}, // A 변화량
                {0, min(ax, remainB), -min(bx, remainA), -min(bx, remainC), min(cx, remainB), 0}, // B 변화량
                {min(ax, remainC), 0, 0, min(bx, remainC), -min(cx, remainB), -min(cx, remainA)}  // C 변화량
        };

        return dx;
    }

}
