package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Yoona_컨베이어벨트_20055 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int count = 0;
        Convey[] conveys = new Convey[2 * n];
        st = new StringTokenizer(reader.readLine());
        for (int i = 0; i < 2 * n; i++) {
            conveys[i] = new Convey(Integer.parseInt(st.nextToken()));
            if(conveys[i].w == 0){
                count++;
            }
        }

        int answer = 0;
        while (count < k){
            // 컨베이어 벨트 이동
            Convey tmp = conveys[2 * n - 1];
            for (int i = 2 * n - 1; i > 0; i--) {
                conveys[i] = conveys[i - 1];
            }
            conveys[0] = tmp;

            conveys[n - 1].robot = false; //로봇 하차

            for (int i = n - 1; i > 0; i--) { //로봇 이동
                if(conveys[i].w > 0 && !conveys[i].robot && conveys[i - 1].robot){
                    conveys[i].comeRobot();
                    conveys[i - 1].goRobot();
                    if(conveys[i].w == 0){
                        count++;
                    }
                }
            }

            conveys[n - 1].robot = false; //로봇 하차

            if(conveys[0].w > 0 && !conveys[0].robot){ // 로봇 승차
                conveys[0].comeRobot();
                if(conveys[0].w == 0){
                    count++;
                }
            }
            answer++;
        }

        System.out.println(answer);
    }

    static class Convey {
        public int w;
        public boolean robot = false;

        public Convey(int w) {
            this.w = w;
        }

        public void comeRobot(){
            w--;
            robot = true;
        }

        public void goRobot(){
            robot = false;
        }
    }
}
