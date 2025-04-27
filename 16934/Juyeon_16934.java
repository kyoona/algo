import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Juyeon_16934 {

    private static Map<String, Integer> count = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Trie trie = new Trie();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            String nickname = br.readLine();
            sb.append(trie.getAlias(nickname)).append("\n");
        }

        System.out.println(sb);
    }

    static class Node {
        Map<Character, Node> child = new HashMap<>();
    }

    static class Trie {
        Node root = new Node();

        String getAlias(String nickname) {
            Node node = root;
            int len = nickname.length();
            boolean uniquePrefix = false;

            int idx = 0;

            for (char c : nickname.toCharArray()) {
                if (!node.child.containsKey(c)) {
                    node.child.put(c, new Node());

                    if (!uniquePrefix) {
                        len = idx + 1;
                        uniquePrefix = true;
                    }
                }

                node = node.child.get(c);
                idx++;
            }

            int x = count.getOrDefault(nickname, 0) + 1;
            count.put(nickname, x);

            return x == 1 ? nickname.substring(0, len) : nickname.substring(0, len) + x;
        }
    }
}
