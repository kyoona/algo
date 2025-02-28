package org.example;

import java.util.*;

public class Yoona_138576 {
    public int solution(int k, int[] tangerine) {
        PriorityQueue<Integer> queue = countOrange(tangerine);
        int answer = 0;
        while(!queue.isEmpty()){
            k -= queue.poll();
            answer++;
            if(k <= 0){
                break;
            }
        }

        return answer;
    }

    public PriorityQueue<Integer> countOrange(int[] tangerine){
        Map<Integer, Integer> weightMap = new HashMap<>(); //무게, 개수
        for(int weight : tangerine){
            weightMap.put(weight, weightMap.getOrDefault(weight, 0) + 1);
        }

        PriorityQueue<Integer> queue= new PriorityQueue<>(Comparator.reverseOrder());
        for(Integer count : weightMap.values()){
            queue.add(count);
        }

        return queue;
    }
}
