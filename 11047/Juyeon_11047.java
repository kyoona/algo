import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Juyeon_11047 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] coin = new int[N];

        for (int i = 0; i < N; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }

        int count = 0;
        int idx = N - 1;

        while (K != 0) {
            count += K / coin[idx];
            K %= coin[idx--];
        }

        System.out.println(count);
    }
}

/*
10 4200
1
5
10
50
100
500
1000
5000
10000
50000

6
 */

/*
10 4790
1
5
10
50
100
500
1000
5000
10000
50000

12
 */