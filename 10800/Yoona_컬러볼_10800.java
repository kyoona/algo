package org.example;

import java.io.*;
import java.util.*;

public class Yoona_컬러볼_10800 {

    public static int n;
    public static int[][] ball;
    public static List<Integer> sortedBall = new ArrayList<>();
    public static Map<Integer, List<Integer>> sumForcolor = new HashMap<>(); //색깔별 누적합
    public static List<Integer> sumForAll = new ArrayList<>(); //전체 누적 합

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(reader.readLine());
        ball = new int[n][2]; //색깔, 크기

        Map<Integer, List<Integer>> ballMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(reader.readLine());
            int color = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());
            ball[i][0] = color;
            ball[i][1] = size;

            if(!ballMap.containsKey(color)){
                ballMap.put(color, new ArrayList());
            }
            ballMap.get(color).add(size);
        }

        //색깔 별 누적 합 계산
        for (Integer color : ballMap.keySet()) {
            Collections.sort(ballMap.get(color));
            sumForcolor.put(color, new ArrayList<>());
            calPrefixSum(ballMap.get(color), sumForcolor.get(color));
        }

        //전체 누적합 계산
        for (int i = 0; i < n; i++) {
            sortedBall.add(ball[i][1]);
        }
        Collections.sort(sortedBall);
        calPrefixSum(sortedBall, sumForAll);

        for (int i = 0; i < n; i++) {
            int idx = findIdxForAll(ball[i][1], sortedBall);
            int idxForColor = findIdxForAll(ball[i][1], ballMap.get(ball[i][0]));
            int result = sumForAll.get(idx) - sumForcolor.get(ball[i][0]).get(idxForColor);
            writer.write(result + "\n");
        }

        writer.flush();
    }

    public static void calPrefixSum(List<Integer> sizeList, List<Integer> prefixSum) {
        int sum = 0;
        for (Integer size : sizeList) {
            sum += size;
            prefixSum.add(sum);
        }
    }

    public static int findIdxForAll (int size, List<Integer> balls){
        int l = 0;
        int r = balls.size() - 1;
        while (l < r){
            int mid = (l + r) / 2;
            if (size > balls.get(mid)) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return l;
    }
}
