package org.example;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

public class Yoona_문자열폭발_9935 {

    public static final int EXCP = -1;

    public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String explosion = scanner.nextLine();

        Node[] nodes = new Node[str.length() + 1];
        for (int i = 0; i < str.length() + 1; i++) {
            nodes[i] = new Node(i - 1);
        }

        for (int i = 0; i < str.length(); i++) {
            Integer curOrder = nodes[i].prevIdx == -1 ? 1 : nodes[nodes[i].prevIdx].order + 1;
            if (str.charAt(i) == explosion.charAt(curOrder - 1)) {
                nodes[i].order = curOrder;
                if(curOrder == explosion.length()){
                    int prevIdx = i;
                    for (int j = 0; j < explosion.length(); j++) {
                        nodes[prevIdx].order = EXCP;
                        prevIdx = nodes[prevIdx].prevIdx;
                    }
                    nodes[i + 1].prevIdx = prevIdx;
                }
            } else if (str.charAt(i) == explosion.charAt(0)) {
                nodes[i].order = 1;
            } else {
                nodes[i].order = 0;
            }
        }

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if(nodes[i].order != EXCP){
                ans.append(str.charAt(i));
            }
        }

        writer.write(ans.length() == 0 ? "FRULA" : ans.toString());
        writer.flush();
    }

    static class Node {
        public int order;
        public int prevIdx;

        public Node(int prevIdx) {
            this.prevIdx = prevIdx;
        }
    }
}
