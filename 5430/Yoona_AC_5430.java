package org.example;

import java.io.*;

public class Yoona_AC_5430 {

    public static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(reader.readLine());

        for (int i = 0; i < t; i++) {
            String ins = reader.readLine();
            int n = Integer.parseInt(reader.readLine());
            int[] arr = inputArr();

            int l = 0;
            int r = arr.length - 1;
            boolean flag = true; // true 순방향 false 역방향
            boolean error = false;
            for (int j = 0; j < ins.length(); j++) {
                if(ins.charAt(j) == 'R'){
                    flag = !flag;
                } else if (ins.charAt(j) == 'D') {
                    if(l > r){
                        error = true;
                        break;
                    }
                    if (flag) l++;
                    else r--;
                }
            }
            print(arr, l, r, flag, error);
        }
        writer.flush();
    }

    public static void print(int[] arr, int l, int r, boolean flag, boolean error) throws IOException {
        if(error){
            writer.write("error\n");
            return;
        }

        writer.write("[");
        if (flag) {
            for (int i = l; i <= r; i++) {
                writer.write(String.valueOf(arr[i]));
                if(i != r) writer.write(",");
            }
        } else {
            for (int i = r; i >= l; i--) {
                writer.write(String.valueOf(arr[i]));
                if(i != l) writer.write(",");
            }
        }
        writer.write("]\n");
    }

    public static int[] inputArr() throws IOException {
        String input = reader.readLine();
        String[] split = input.substring(1, input.length() - 1).split(",");

        int[] arr = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            if(split[i] == "") {
                return new int[0];
            }
            arr[i] = Integer.parseInt(split[i]);
        }

        return arr;
    }
}
