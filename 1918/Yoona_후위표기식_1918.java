package org.example;

import java.util.*;

public class Yoona_후위표기식_1918 {

    public static Map<Character, Integer> priority = new HashMap<>();
    public static Character BRACKET = '?';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();

        priority.put('+', 1);
        priority.put('-', 1);
        priority.put('*', 2);
        priority.put('/', 2);

        Stack<Character> signStack = new Stack<>();
        Stack<Character> ans = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if(isSign(ch)){
                if(priority.get(ch) > priority.getOrDefault(ans.peek(), Integer.MAX_VALUE)){ //현재 부호가 이전 부호보다 우선순위가 높을때 -> 이전 부호 후순위
                    signStack.push(ans.pop());
                }
                signStack.push(ch);
            }else if (isChar(ch)) {
                ans.push(ch);
                while (!signStack.isEmpty() && signStack.peek() != BRACKET) ans.push(signStack.pop()); //괄호가 아닐때까지만 꺼냄
            }else if(ch == '(') {
                signStack.push(BRACKET);
            } else if (ch == ')') {
                ans.push(signStack.pop()); // 괄호 1개만 제거하고 괄호로 묶였다는 표시 ans에 추가
                while (!signStack.isEmpty() && signStack.peek() != BRACKET) ans.push(signStack.pop()); // 이전 부호 붙여줌
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Character ch : ans) {
            if(ch != BRACKET) sb.append(ch);
        };
        System.out.println(sb);
    }

    public static boolean isChar(Character ch) {
        return ch - 'A' >= 0 && ch - 'A' < 26;
    }

    public static boolean isSign(Character ch) {
        return priority.containsKey(ch);
    }
}