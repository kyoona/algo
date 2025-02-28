import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Juyeon_138476 {
    public int solution(int k, int[] tangerine) {
        int answer = 0;

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i : tangerine) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        List<Integer> keySet = new ArrayList<>(map.keySet());
        keySet.sort((o1, o2) -> map.get(o2) - map.get(o1));

        for (int i : keySet) {
            k -= map.get(i);
            answer++;
            if (k <= 0) break;
        }

        return answer;
    }
}
