package org.example;

import java.util.*;

class Yoona_수식최대화_67257 {

    public static List<Long> nums = new ArrayList<>();
    public static List<Character> opt = new ArrayList<>();
    public static Character[][] prtOpt = {
            {'+', '-', '*'},
            {'-', '+', '*'},
            {'+', '*', '-'},
            {'-', '*', '+'},
            {'*', '+', '-'},
            {'*', '-', '+'}
    };

    public long solution(String expression) {
        parseExpression(expression);

        long answer = 0;
        for(int i = 0; i < 6; i++){
            Deque<Long> numQue = new ArrayDeque<>(nums);
            Deque<Character> optQue = new ArrayDeque<>(opt);

            for(int j = 0; j < 3; j ++){
                int optSize = optQue.size();
                for(int k = 0; k < optSize; k++){
                    Character curOpt = optQue.poll();

                    if(curOpt == prtOpt[i][j]){
                        long result = cal(curOpt, numQue.poll(), numQue.poll());
                        numQue.addFirst(result);
                    }else{
                        numQue.add(numQue.poll());
                        optQue.add(curOpt);
                    }

                    if(k == optSize - 1) numQue.add(numQue.poll());
                }
            }
            answer = Long.max(Math.abs(numQue.poll()), answer);
        }

        return answer;
    }

    public long cal(Character calOpt, long a, long b){
        if(calOpt == '+') return a + b;
        else if(calOpt == '-') return a - b;
        else return a * b;
    }

    public void parseExpression(String expression){
        int idx = 0;
        for(int i = 0; i < expression.length(); i++){
            if(expression.charAt(i) == '*' || expression.charAt(i) == '+' || expression.charAt(i) == '-'){
                opt.add(expression.charAt(i));
                nums.add(Long.parseLong(expression.substring(idx, i)));
                idx = i + 1;
            }
        }
        nums.add(Long.parseLong(expression.substring(idx, expression.length())));
    }
}