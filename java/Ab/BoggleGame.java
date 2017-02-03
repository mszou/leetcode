// Boggle game	参考LC212 word search II
//	1. 题目是给定一个2d matrix of letters和一个dictionary，找出一条path包含最多的存在于字典的word个数 
//	讨论了半天算法，真到写code的时候时间就来不及了，test case没来得及写，也没来得及优化。
//	用了dfs backtracking 暴力解法。应该就是挂在这一轮
//	2. 但是呢比如你现在走了一个词apple，那么a,p,p,l,e这几个char的位置不能继续用了，于是给你一个board，一个dict，
//	让你计算最多能有多少个valid单词出现在这个board上

import java.util.*;
// idea: use Trie data structure. First use the given words to build a Trie tree.
// then DFS + backtracking to search words. use a set or m*n array to mark visted cells.
public class BoggleGame {
	class TrieNode {
		TrieNode[] children;
		boolean isWord;
		public TrieNode() {
			children = new TrieNode[26];
			isWord = false;
		}
	}
	
	int max = 0;
	String maxPath = "";
	TrieNode root = new TrieNode();
	int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};
	
	public String findMax(char[][] board, String[] dic) {
		for (String word : dic) {
			insertTrie(root, word);
		}
		Set<Integer> visited = new HashSet<>();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (root.children[board[i][j] - 'a'] != null) {
					dfs(board, root, visited, "", i, j, 0);
				}
			}
		}
		return maxPath;
	}

	private void insertTrie(TrieNode root, String word) {
		if (word.length() == 0) {
			return;
		}
		TrieNode curr = root;
		for (char c : word.toCharArray()) {
			if (curr.children[c - 'a'] == null) {
				curr.children[c - 'a'] = new TrieNode();
			}
			curr = curr.children[c - 'a'];
		}
		curr.isWord = true;
	}

	private void dfs(char[][] board, TrieNode pos, Set<Integer> visited, String path, int i, int j, int count) {
		if (pos.isWord) {
			if (count + 1 > max) {
				max = count + 1;
				maxPath = path;
			}
			pos.isWord = false;
			dfs(board, root, visited, path, i, j, count + 1);
			pos.isWord = true;
		}
		visited.add(i * board[0].length + j);
		for (int[] d : dir) {
			int x = i + d[0];
			int y = j + d[1];
			if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || visited.contains(x * board[0].length + y)) {
				continue;
			}
			if (pos.children[board[i][j] - 'a'] != null) {
				dfs(board, pos.children[board[i][j] - 'a'], visited, path + board[i][j], x, y, count);
			} else {
				dfs(board, root, visited, path + board[i][j], x, y, count);	// delete else part when the words have to be connected
			}
		}
		visited.remove(i * board[0].length + j);
		return;
	}

	public static void main(String[] args) {
		BoggleGame s = new BoggleGame();
		char[][] board = {{'a','b','c','o','m'},{'a','m','n','d','f'},{'p','g','p','q','k'},{'o','b','c','d','e'},{'m','f','g','s','w'}};
		String[] dic = {"abc","co","mf","amndf","cnp","apom","cdsw","pqk","bm"};
		System.out.println(s.findMax(board, dic));
		System.out.println(s.max);
	}

}
