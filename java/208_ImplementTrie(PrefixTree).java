/**
 * Implement a trie with insert, search, and startsWith methods.
 * Note:
 * You may assume that all inputs are consist of lowercase letters a-z.
 */

public class Trie {
    /** Initialize your data structure here. */
    class TrieNode {
        // public char val;     // can either have a val ( = 'a' + index), or use index as ID
        public TrieNode[] children;  // possible next letters are its children
        public boolean isWord;  // a flag to indicate whether it is the end of a word
        public TrieNode() {
            children = new TrieNode[26];   // at most 26 children (for a-z)
            isWord = false;
        }
    }
    private TrieNode root;
    
    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode node = root;   // start from root
        for (char c : word.toCharArray()) {
        	if (node.children[c - 'a'] == null) {
        		node.children[c - 'a'] = new TrieNode();
        	}
        	node = node.children[c - 'a']; // go along the path
        }
        node.isWord = true; // mark the end of the word
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = root;   // start from root
        for (char c : word.toCharArray()) {
        	if (node.children[c - 'a'] == null) {  // no path
        		return false;
        	}
        	node = node.children[c - 'a'];
        }
        return node.isWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
        	if (node.children[c - 'a'] == null) {
        		return false;
        	}
        	node = node.children[c - 'a'];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */