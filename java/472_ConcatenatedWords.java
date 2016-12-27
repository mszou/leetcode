/**
 * Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.
 * A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.
 * Example:
 * Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 * Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
 * Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats"; 
 * "dogcatsdog" can be concatenated by "dog", "cats" and "dog"; 
 * "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
 * Note:
 * 1. The number of elements of the given array will not exceed 10,000
 * 2. The length sum of elements in the given array will not exceed 600,000.
 * 3. All the input string will only include lower case letters.
 * 4. The returned elements order does not matter.
 */

public class Solution {
	// sol 1: DP, add previous words in a set as dict, for the current word, if substring(0, j)
	// is a concatenation and substring(j) is also in the dict, then the word is a concatenation
	public List<String> findAllConcatenatedWordsInADict(String[] words) {
		List<String> res = new ArrayList<>();
		Set<String> preWords = new HashSet<>();
		// sort words from shorter to longer, cuz word can only be composed with shorter words
		Arrays.sort(words, new Comparator<String>() {
			public int compare(String s1, String s2) {
				return s1.length() - s2.length();
			}
		});
		for (String word : words) {
			if (canForm(word, preWords)) {
				res.add(word);
			}
			preWords.add(word);
		}
		return res;
	}

	// check whether the word can be formed by words in the dict
	private boolean canForm(String word, Set<String> dict) {
		if (dict.isEmpty()) {
			return false;
		}
		boolean[] conca = new boolean[word.length() + 1];
		conca[0] = true;
		for (int i = 1; i <= word.length(); i++) {
			for (int j = 0; j < i; j++) {
				if (!conca[j]) {
					continue;
				}
				if (dict.contains(word.substring(j, i))) {
					conca[i] = true;
					break;
				}
			}
		}
		return conca[word.length()];
	}

	// sol 2: Trie + DFS
	class TrieNode {
		String word;
		TrieNode[] children;
		boolean isEnd;
		boolean isCombo;
		boolean added;
		public TrieNode(){
			this.word = new String();
			this.children = new TrieNode[26];
			this.isEnd = false;
			this.isCombo = false;
			this.added = false;
		}
	}

	private TrieNode root;
	private List<String> res;

	// add word to the Trie
	private void addWord(String str) {
		if (str == null || str.length() == 0) {
			return;
		}
		TrieNode node = root;
		for (char c : str.toCharArray()) {
			if (node.children[c - 'a'] == null) {
				node.children[c - 'a'] = new TrieNode();
			}
			node = node.children[c - 'a'];
		}
		node.isEnd = true;
		node.word = str;
	}

	public List<String> findAllConcatenatedWordsInADict(String[] words) {
		root = new TrieNode();
		for (String word : words) {
			addWord(word);	// Build trie with given words
		}
		res = new ArrayList<String>();
		dfs(root, 0);
		return res;
	}

	private void dfs(TrieNode node, int count) {
		if (node.isEnd && !node.added && count > 1) {
			node.isCombo = true;
			node.added = true;
			res.add(node.word);
		}
		searchWord(node, root, count);
	}

	// node1 goes along the current word, node2 goes along the trie tree from root
	private void searchWord(TrieNode node1, TrieNode node2, int count) {
		if (node2.isCombo) {	// there is concatenated word(s)  node2
			return;
		}
		if (node2.isEnd) {	// finish a word
			dfs(node1, count + 1);	// continue to check the rest part
		}
		for (int i = 0; i < 26; i++) {
			if (node1.children[i] != null && node2.children[i] != null) {
				searchWord(node1.children[i], node2.children[i], count);
			}
		}
	}
}