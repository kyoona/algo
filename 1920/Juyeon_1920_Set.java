import java.io.*;
import java.util.*;

public class Juyeon_1920_Set {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        Set<Integer> set = new HashSet<>();

        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        while (st.hasMoreTokens()) {
            int x = Integer.parseInt(st.nextToken());
            set.add(x);
        }

        int M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) {
            int e = Integer.parseInt(st.nextToken());

            if (set.contains(e)) {
                sb.append("1").append('\n');
            } else {
                sb.append("0").append('\n');
            }
        }

        System.out.println(sb.toString().trim());
    }
}
