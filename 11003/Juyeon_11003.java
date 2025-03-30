import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Juyeon_11003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        Deque<int[]> dq = new ArrayDeque<>();

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int e = Integer.parseInt(st.nextToken());

            while (!dq.isEmpty() && dq.peekLast()[1] > e) {
                dq.pollLast();
            }

            dq.add(new int[]{i, e});

            if (dq.peekFirst()[0] < i - L + 1) {
                dq.poll();
            }

            bw.write(dq.peekFirst()[1] + " ");
        }

        bw.flush();
        bw.close();
    }

    // 우선순위 큐는 시간 초과
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        int N = Integer.parseInt(st.nextToken());
//        int L = Integer.parseInt(st.nextToken());
//
//        Queue<Integer> q = new LinkedList<>();
//        PriorityQueue<Integer> pq = new PriorityQueue<>();
//
//        st = new StringTokenizer(br.readLine());
//
//        for (int i = 0; i < N; i++) {
//            int k = Integer.parseInt(st.nextToken());
//
//            q.add(k);
//            pq.add(k);
//
//            if (q.size() > L) {
//                int e = q.poll();
//                pq.remove(e);
//            }
//
//            bw.write(pq.peek() + " ");
//        }
//
//        bw.flush();
//        bw.close();
//    }
}
