/**
 * Implement a trie with insert, search, and startsWith methods.
 * Note:
 * You may assume that all inputs are consist of lowercase letters a-z.
 */

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");

class TrieNode {
    // Initialize your data structure here.
    // idea: use an Array of TrieNode (length = 26) to represent children for each node
    // public char val;
    public boolean isWord;
    public TrieNode[] children = new TrieNode[26];
    public TrieNode() {}
    // TrieNode(char c) {
    // 	TrieNode node = new TrieNode();
    // 	node.val = c;
    // }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
        	char c = word.charAt(i);
        	if (node.children[c - 'a'] == null) {
        		node.children[c - 'a'] = new TrieNode();
        	}
        	node = node.children[c - 'a'];
        }
        node.isWord = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
        	char c = word.charAt(i);
        	if (node.children[c - 'a'] == null) {
        		return false;
        	}
        	node = node.children[c - 'a'];
        }
        return node.isWord;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
        	char c = prefix.charAt(i);
        	if (node.children[c - 'a'] == null) {
        		return false;
        	}
        	node = node.children[c - 'a'];
        }
        return true;
    }
}
