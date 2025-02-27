package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Yoona_1541 {

    public static int minusAns = 0;
    public static int plusAns = 0;
    public static boolean hasMinus = false;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();

        String str = "";
        for (int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == '-'){
                calculate(Integer.parseInt(str.toString()));
                hasMinus = true;
                str = "";
            } else if (input.charAt(i) == '+') {
                calculate(Integer.parseInt(str.toString()));
                str = "";
            } else if (input.charAt(i) != '+') {
                str += String.valueOf(input.charAt(i));
            }
        }
        calculate(Integer.parseInt(str.toString()));

        System.out.println(plusAns - minusAns);
    }

    public static void calculate(int n){
        if(hasMinus){
            minusAns += n;
        }else {
            plusAns += n;
        }
    }
}
