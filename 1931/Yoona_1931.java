package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Yoona_1931 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        PriorityQueue<Meeting> queue = new PriorityQueue<>(Comparator.comparingInt(m -> m.start));
        for(int i = 0; i < n; i++){
            String[] input = reader.readLine().split(" ");
            queue.add(new Meeting(Integer.parseInt(input[0]), Integer.parseInt(input[1])));
        }

        int ans = 1;
        Meeting lastMeet = queue.poll();
        while(!queue.isEmpty()){
            Meeting pick = queue.poll();
            if(pick.start < lastMeet.end && pick.end <= lastMeet.end){
                if (pick.end == lastMeet.start) {
                    ans++;
                } else {
                    lastMeet = pick;
                }
            } else if (pick.start >= lastMeet.end) {
                lastMeet = pick;
                ans++;
            }
        }

        System.out.print(ans);
    }

    public static class Meeting{
        public int start;
        public int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
