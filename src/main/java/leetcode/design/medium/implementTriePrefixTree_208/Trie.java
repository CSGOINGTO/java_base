package leetcode.design.medium.implementTriePrefixTree_208;

public class Trie {

    private boolean isEnd;

    private Trie[] next;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        next = new Trie[26];
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        Trie root = this;
        char[] wordChars = word.toCharArray();
        for (int i = 0; i < wordChars.length; i++) {
            if (root.next[wordChars[i] - 'a'] == null) {
                root.next[wordChars[i] - 'a'] = new Trie();
            }
            root = root.next[wordChars[i] - 'a'];
        }
        root.isEnd = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        Trie root = this;
        char[] wordChars = word.toCharArray();
        for (int i = 0; i < wordChars.length; i++) {
            if (root.next[wordChars[i] - 'a'] == null) return false;
            root = root.next[wordChars[i] - 'a'];
        }
        return root.isEnd;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        Trie root = this;
        char[] prefixChars = prefix.toCharArray();
        for (int i = 0; i < prefixChars.length; i++) {
            if (root.next[prefixChars[i] - 'a'] == null) return false;
            root = root.next[prefixChars[i] - 'a'];
        }
        return true;
    }
}
