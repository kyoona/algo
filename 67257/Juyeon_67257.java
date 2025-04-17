import java.util.ArrayList;
import java.util.List;

public class Juyeon_67257 {
    public long solution(String expression) {
        long answer = Long.MIN_VALUE;

        String[][] op = {{"+", "-", "*"}, {"+", "*", "-"}, {"-", "*", "+"},
                {"-", "+", "*"}, {"*", "-", "+"}, {"*", "+", "-"}};

        List<String> list = new ArrayList<>();

        int idx = 0;

        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '+' || expression.charAt(i) == '-' || expression.charAt(i) == '*') {
                list.add(expression.substring(idx, i));
                list.add(expression.charAt(i) + "");
                idx = i + 1;
            }
        }

        list.add(expression.substring(idx));

        for (int i = 0; i < 6; i++) {
            List<String> temp = new ArrayList<>(list);

            for (int k = 0; k < 3; k++) {
                for (int j = 0; j < temp.size(); j++) {
                    if (temp.get(j).equals(op[i][k])) {
                        String v = calc(temp.get(j - 1), temp.get(j), temp.get(j + 1));

                        // 계산 결과값 삽입
                        temp.set(j - 1, v);

                        // 연산자, 피연산자 한 개 지우고
                        temp.remove(j);
                        temp.remove(j);

                        // 인덱스 조정
                        j--;
                    }
                }
            }

            answer = Math.max(answer, Math.abs(Long.parseLong(temp.getFirst())));
        }

        return answer;
    }

    private static String calc(String str1, String op, String str2) {
        long x = Long.parseLong(str1);
        long y = Long.parseLong(str2);

        if (op.equals("+")) return x + y + "";
        else if (op.equals("-")) return x - y + "";
        else return x * y + "";
    }
}
