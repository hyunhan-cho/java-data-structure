import java.util.HashMap;
import java.util.Map;

public class TrieSet {
    private Node root;

    public TrieSet() {
        root = new Node('\0', false);  // 트라이 루트 초기화
    }

    public void add(String key) {
        Node current = root;
        for (char c : key.toCharArray()) {
            current.next.putIfAbsent(c, new Node(c, false));
            current = current.next.get(c);
        }
        current.isKey = true;
    }

    public boolean contains(String key) {
        Node current = root;
        for (char c : key.toCharArray()) {
            if (!current.next.containsKey(c)) {
                return false;
            }
            current = current.next.get(c);
        }
        return current.isKey;
    }

    public void remove(String key) {
        remove(root, key, 0);
    }

    private boolean remove(Node node, String key, int index) {
        if (index == key.length()) {
            if (!node.isKey) return false;
            node.isKey = false;
            return node.next.isEmpty();
        }

        char ch = key.charAt(index);
        Node nextNode = node.next.get(ch);
        if (nextNode == null) return false;

        boolean shouldDeleteCurrentNode = remove(nextNode, key, index + 1);

        if (shouldDeleteCurrentNode) {
            node.next.remove(ch);
            return node.next.isEmpty() && !node.isKey;
        }

        return false;
    }

    // Node 클래스는 내부에서 정의됨
    private static class Node {
        private char ch;
        private boolean isKey;
        private Map<Character, Node> next;

        private Node(char c, boolean b) {
            ch = c;
            isKey = b;
            next = new HashMap<>();
        }
    }

        public static void main(String[] args) {
        TrieSet trie = new TrieSet();
        trie.add("sam");
        trie.add("same");
        trie.add("sad");

        System.out.println(trie.contains("sam"));  // true
        System.out.println(trie.contains("sa"));   // false
        System.out.println(trie.contains("same")); // true

        trie.remove("sam");
        System.out.println(trie.contains("sam"));  // false
    }
}

