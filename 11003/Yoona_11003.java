package org.example;

import java.util.ArrayDeque;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Yoona_11003 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(scanner.nextLine());

        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(scanner.nextLine());
        ArrayDeque<Num> window = new ArrayDeque<>();
        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int ni = Integer.parseInt(st.nextToken());
            while (!window.isEmpty() && window.peekLast().n > ni) window.pollLast();
            window.add(new Num(ni, i));

            if(i - window.peekFirst().i >= l) window.pollFirst();
            ans.append(window.peekFirst().n + " ");
        }

        System.out.println(ans);
    }

    static class Num {

        public int n;
        public int i;

        public Num(int n, int i) {
            this.n = n;
            this.i = i;
        }
    }
}
