/**
 * Design a data structure that supports the following two operations:
 * void addWord(word)
 * bool search(word)
 * search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.
 * For example:
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 * Note:
 * You may assume that all words are consist of lowercase letters a-z.
 * You should be familiar with how a Trie works. If not, please work on this problem: Implement Trie (Prefix Tree) first.
 */

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");

// idea: first use Trie as the data structure. 
// When searching word with '.', DFS and backtracking to check all the children letters

class TrieNode {
	public TrieNode[] children;
	public boolean isWord;
	public TrieNode() {
		children = new TrieNode[26];
		isWord = false;
	}
}

public class WordDictionary {
	private TrieNode root;

	public WordDictionary() {
		root = new TrieNode();
	}

    // Adds a word into the data structure.
    public void addWord(String word) {
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

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return find(word, 0, root);
    }

    private boolean find(String word, int index, TrieNode node) {
    	if (index == word.length()) {
    		return node.isWord;
    	}
    	char c = word.charAt(index);
    	if (c == '.') {
    		for (int i = 0; i < 26; i++) {
    			if (node.children[i] != null) {
    				if (find(word, index + 1, node.children[i])) {
    					return true;
    				}
    			}
    		}
    		return false;
    	} else {
    		return (node.children[c - 'a'] != null && find(word, index + 1, node.children[c - 'a']));
    	}
    }
}
