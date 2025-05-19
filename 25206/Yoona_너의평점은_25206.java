package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Yoona_너의평점은_25206 {

    public static Map<String, Double> scoreMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        init();

        double gradeTotal = 0;
        double multiply = 0;
        for (int i = 0; i < 20; i++) {
            st = new StringTokenizer(reader.readLine());
            st.nextToken();
            double grade = Double.parseDouble(st.nextToken());
            double score = scoreMap.get(st.nextToken());

            if(score != -1){
                gradeTotal += grade;
                multiply += grade * score;
            }
        }
        System.out.println(multiply / gradeTotal);
    }

    public static void init(){
        scoreMap.put("A+", 4.5);
        scoreMap.put("A0", 4.0);
        scoreMap.put("B+", 3.5);
        scoreMap.put("B0", 3.0);
        scoreMap.put("C+", 2.5);
        scoreMap.put("C0", 2.0);
        scoreMap.put("D+", 1.5);
        scoreMap.put("D0", 1.0);
        scoreMap.put("F", 0.0);
        scoreMap.put("P", -1.0);
    }
}
