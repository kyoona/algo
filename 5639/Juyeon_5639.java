import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Juyeon_5639 {

    private static final List<Integer> preorder = new ArrayList<>();
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while ((line = br.readLine()) != null && !line.isEmpty()) {
            preorder.add(Integer.parseInt(line));
        }

        postorder(0, preorder.size() - 1);

        System.out.println(sb);
    }

    private static void postorder(int s, int e) {
        if (s > e) return;

        int root = preorder.get(s);

        int idx = s + 1;
        while (idx <= e && preorder.get(idx) < root) {
            idx++;
        }

        postorder(s + 1, idx - 1);
        postorder(idx, e);
        sb.append(root).append("\n");
    }
}
