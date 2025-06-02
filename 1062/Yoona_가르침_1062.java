package org.example;

import java.io.*;
import java.util.*;

public class Yoona_가르침_1062 {
    static int n, k, max = 0;
    static boolean[] learn = new boolean[26];
    static String[] words;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        if (k < 5) {
            System.out.println(0);
            return;
        }

        words = new String[n];
        for (int i = 0; i < n; i++) {
            String word = reader.readLine();
            words[i] = word.substring(4, word.length() - 4);
        }

        learn['a' - 'a'] = true;
        learn['n' - 'a'] = true;
        learn['t' - 'a'] = true;
        learn['i' - 'a'] = true;
        learn['c' - 'a'] = true;

        dfs(0, 0);
        System.out.println(max);
    }

    static void dfs(int start, int depth) {
        if (depth == k - 5) {
            check();
            return;
        }

        for (int i = start; i < 26; i++) {
            if (!learn[i]) {
                learn[i] = true;
                dfs(i + 1, depth + 1);
                learn[i] = false;
            }
        }
    }

    static void check() {
        int count = 0;
        for (String word : words) {
            boolean readable = true;
            for (int i = 0; i < word.length(); i++) {
                if (!learn[word.charAt(i) - 'a']) {
                    readable = false;
                    break;
                }
            }
            if (readable) count++;
        }
        max = Math.max(max, count);
    }
}

