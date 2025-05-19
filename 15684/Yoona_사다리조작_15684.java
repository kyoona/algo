package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Yoona_사다리조작_15684 {

    static int n, m, h;
    static boolean[][] ladder;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        ladder = new boolean[h + 1][n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ladder[a][b] = true;
        }

        for (int i = 0; i <= 3; i++) {
            dfs(1, 1, 0, i);
            if (ans != Integer.MAX_VALUE) break;
        }

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    static void dfs(int x, int y, int count, int limit) {
        if (count > limit) return;

        if (check()) {
            ans = count;
            return;
        }

        for (int i = x; i <= h; i++) {
            for (int j = (i == x ? y : 1); j < n; j++) {
                if (canMake(i, j)) {
                    ladder[i][j] = true;
                    dfs(i, j + 2, count + 1, limit);
                    ladder[i][j] = false;
                }
            }
        }
    }

    static boolean check() {
        for (int i = 1; i <= n; i++) {
            int y = i;
            for (int x = 1; x <= h; x++) {
                if (ladder[x][y]) {
                    y++;
                }
                else if (y - 1 > 0 && ladder[x][y - 1]) {
                    y--;
                }
            }
            if (y != i) {
                return false;
            }
        }
        return true;
    }

    static boolean canMake(int x, int y) {
        if (ladder[x][y] || (y - 1 > 0 && ladder[x][y - 1]) || (y + 1 <= n && ladder[x][y + 1])){
            return false;
        }
        return true;
    }
}
