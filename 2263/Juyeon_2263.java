import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Juyeon_2263 {

    private static int[] postorder;
    private static Map<Integer, Integer> inorderIdx;
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[] inorder = new int[n + 1];
        postorder = new int[n + 1];

        inorderIdx = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            inorder[i] = Integer.parseInt(st.nextToken());
            inorderIdx.put(inorder[i], i);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            postorder[i] = Integer.parseInt(st.nextToken());
        }

        sb = new StringBuilder();

        buildPreorder(1, n, 1, n);

        System.out.println(sb);
    }

    private static void buildPreorder(int inS, int inE, int postS, int postE) {
        if (inS > inE || postS > postE) return;

        int root = postorder[postE];
        sb.append(root).append(" ");

        int idx = inorderIdx.get(root);

        int left = idx - inS;

        buildPreorder(inS, idx - 1, postS, postS + left - 1);
        buildPreorder(idx + 1, inE, postS + left, postE - 1);
    }
}
