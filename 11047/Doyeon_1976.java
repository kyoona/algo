import java.io.*;
import java.util.*;

public class Doyeon_1976 {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        // 배열 초기화
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int connected = Integer.parseInt(st.nextToken()); // 두 도시의 연결 정보
                if (connected == 1) {
                    union(i, j); // 연결되어 있다면 합집합 연산 수행
                }
            }
        }

        // 여행 경로 입력
        st = new StringTokenizer(br.readLine());
        int[] route = new int[M];
        for (int i = 0; i < M; i++) {
            route[i] = Integer.parseInt(st.nextToken());
        }

        // 여행 경로의 모든 도시가 같은 집합에 있는지 확인
        boolean possible = true;
        int root = find(route[0]);
        for (int i = 1; i < M; i++) {
            if (find(route[i]) != root) { // 대표 노드가 다르면 다른 집합
                possible = false;
                break;
            }
        }

        System.out.println(possible ? "YES" : "NO");
    }

    //  경로 압축을 적용한 Find 연산
    static int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]); // 부모를 루트로 갱신
        return parent[x];
    }

    // Union 연산 (두 집합을 합침)
    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB)  // 루트가 다르면 합집합 수행
            parent[rootB] = rootA;
    }
}
