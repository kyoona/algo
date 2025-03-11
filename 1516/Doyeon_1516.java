import java.util.*;

public class Doyeon_1516 {
    static final int MAX = 10001;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();  // 건물의 수

        int[] time = new int[n + 1];
        int[] indegree = new int[n + 1];
        int[] resultTime = new int[n + 1];

        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 건물의 짓는 시간 입력
        for (int i = 1; i <= n; i++) {
            time[i] = sc.nextInt();  // 해당 건물을 짓는데 걸리는 시간
            while (true) {
                int prev = sc.nextInt();
                if (prev == -1) break;
                graph[prev].add(i);
                indegree[i]++;
            }
        }

        // 위상 정렬 이용
        Queue<Integer> queue = new LinkedList<>();

        // 진입 차수가 0인 건물들을 큐에 넣기
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
                resultTime[i] = time[i];
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int next : graph[current]) {
                indegree[next]--;
                resultTime[next] = Math.max(resultTime[next], resultTime[current] + time[next]);

                // 진입 차수가 0이 되면 큐에 넣기
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            System.out.println(resultTime[i]);
        }
    }
}
