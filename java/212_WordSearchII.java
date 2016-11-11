/**
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 * For example,
 * Given words = ["oath","pea","eat","rain"] and board =
 * [
 *   ['o','a','a','n'],
 *   ['e','t','a','e'],
 *   ['i','h','k','r'],
 *   ['i','f','l','v']
 * ]
 * Return ["eat","oath"].
 * Note:
 * You may assume that all inputs are consist of lowercase letters a-z.
 * Hint: You would need to optimize your backtracking to pass the larger test. Could you stop backtracking earlier?
 * If the current candidate does not exist in all words' prefix, you could stop backtracking immediately. What kind of data structure could answer such query efficiently? Does a hash table work? Why or why not? How about a Trie? If you would like to learn how to implement a basic trie, please work on this problem: Implement Trie (Prefix Tree) first.
 */

public class Solution {
	// idea: use Trie data structure. First use the given words to build a Trie tree. then use dfs + backtracking
	// to search the board, mark visited positions in the current path as '#', and resume after finishing

	class TrieNode {
		TrieNode[] children = new TrieNode[26];
		String word;
	}

	public TrieNode buildTrie(String[] words) {
		TrieNode root = new TrieNode();
		for (String word : words) {
			TrieNode node = root;
			for (char c : word.toCharArray()) {
				int i = c - 'a';
				if (node.children[i] == null) {
					node.children[i] = new TrieNode();
				}
				node = node.children[i];
			}
			node.word = word;
		}
		return root;
	}

	public List<String> findWords(char[][] board, String[] words) {
		List<String> res = new ArrayList<String>();
		TrieNode root = buildTrie(words);
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				dfs(board, i, j, root, res);
			}
		}
		return res;
	}

	public void dfs(char[][] board, int i, int j, TrieNode node, List<String> res) {
		char c = board[i][j];
		if (c == '#' || node.children[c - 'a'] == null) {	// cannot be a word
			return;
		}
		node = node.children[c - 'a'];
		if (node.word != null) {	// so far is a word
			res.add(node.word);
			node.word = null;	// avoid duplicate
		}
		board[i][j] = '#';	// mark as visited (in current path)
		if (i > 0) {
			dfs(board, i - 1, j, node, res);	// go up
		}
		if (j > 0) {
			dfs(board, i, j - 1, node, res);	// go left
		}
		if (i < board.length - 1) {
			dfs(board, i + 1, j, node, res);	// go down
		}
		if (j < board[0].length - 1) {
			dfs(board, i, j + 1, node, res);	// go right
		}
		board[i][j] = c;	// backtracking
	}
}