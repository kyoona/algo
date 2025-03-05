import java.io.*;
import java.util.*;

public class Doyeon_1717 {
    static int[] parent, rank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 원소 개수
        int m = Integer.parseInt(st.nextToken()); // 연산 개수

        parent = new int[n + 1];
        rank = new int[n + 1];

        // 초기 집합 설정: 각 원소가 자기 자신을 부모로 가짐
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
            rank[i] = 1; // 초기 랭크는 1
        }

        // 연산 수행
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (type == 0) {
                union(a, b); // 합집합 연산
            } else {
                // 같은 집합인지 확인
                if (find(a) == find(b)) {
                    sb.append("YES\n");
                } else {
                    sb.append("NO\n");
                }
            }
        }

        System.out.print(sb); // 결과 한 번에 출력 (출력 최적화)
    }

    // 경로 압축 적용한 find 연산
    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    // 랭크 기반 union 연산
    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            if (rank[rootA] > rank[rootB]) {
                parent[rootB] = rootA;
            } else if (rank[rootA] < rank[rootB]) {
                parent[rootA] = rootB;
            } else {
                parent[rootB] = rootA;
                rank[rootA]++;
            }
        }
    }
}
