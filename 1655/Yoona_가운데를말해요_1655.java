package org.example;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

public class Yoona_가운데를말해요_1655 {

    public static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    public static PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        maxHeap.add(Integer.MIN_VALUE);
        minHeap.add(Integer.MAX_VALUE);
        boolean isLeft = true;
        while (n-- > 0){
            int num = Integer.parseInt(scanner.nextLine());
            if(isLeft && num > minHeap.peek()){
                minHeap.add(num);
                num = minHeap.poll();
            } else if (!isLeft && num < maxHeap.peek()) {
                maxHeap.add(num);
                num = maxHeap.poll();
            }
            if (isLeft) maxHeap.add(num);
            else minHeap.add(num);

            isLeft= !isLeft;
            writer.write(pickMid() + "\n");
        }

        writer.flush();
    }

    public static int pickMid() {
        if (maxHeap.size() >= minHeap.size()) return maxHeap.peek();
        else return minHeap.peek();
    }
}
