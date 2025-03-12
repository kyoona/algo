package org.example;

import java.util.*;

public class Yoona_12985 {
    public int solution(int n, int a, int b)
    {
        Queue<Integer> queue = new ArrayDeque<>();
        for(int i = 1; i < n + 1; i++){
            queue.add(i);
        }

        int count = n;
        int answer = 1;
        while(true){
            for(int i = 0; i < count/2; i++){
                int n1 = queue.poll();
                int n2 = queue.poll();
                if((n1 == a || n1 == b) && (n2 == a || n2 == b)){
                    return answer;
                }
                if(n1 == a || n1 == b){
                    queue.add(n1);
                }else {
                    queue.add(n2);
                }
            }
            count = count / 2;
            answer++;
        }
    }
}
