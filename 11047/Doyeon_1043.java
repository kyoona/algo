import java.io.*;
import java.util.*;

public class Doyeon_1043 {
    static int[] parent;
    static boolean[] knowsTruth;
    static List<List<Integer>> parties = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        knowsTruth = new boolean[N + 1];
        for (int i = 1; i <= N; i++)
            parent[i] = i; // 자기 자신을 부모로 설정

        // 진실을 아는 사람 입력
        st = new StringTokenizer(br.readLine());
        int truthCount = Integer.parseInt(st.nextToken());
        Set<Integer> truthPeople = new HashSet<>(); // 진실을 아는 사람 집합

        for (int i = 0; i < truthCount; i++) {
            int person = Integer.parseInt(st.nextToken());
            knowsTruth[person] = true;
            truthPeople.add(person);
        }

        // 각 파티에 대한 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int partySize = Integer.parseInt(st.nextToken());
            List<Integer> party = new ArrayList<>();
            int first = Integer.parseInt(st.nextToken()); // 첫 번째 사람
            party.add(first);

            // 같은 파티의 사람들을 같은 그룹으로 묶음 (유니온 연산)
            for (int j = 1; j < partySize; j++) {
                int person = Integer.parseInt(st.nextToken());
                party.add(person);
                union(first, person);
            }
            parties.add(party);
        }

        // 진실을 아는 사람들과 연결된 모든 그룹을 확인
        for (int person : truthPeople)
            knowsTruth[find(person)] = true;

        int maxLies = 0;
        for (List<Integer> party : parties)
            if (!knowsTruth[find(party.get(0))]) maxLies++;

        System.out.println(maxLies); // 결과 출력
    }

    // 유니온 파인드의 find 연산 (경로 압축 기법 적용)
    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    // 유니온 파인드의 union 연산 (두 그룹을 합침)
    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) parent[rootB] = rootA; // 한쪽을 다른 쪽의 부모로 설정
    }
}
