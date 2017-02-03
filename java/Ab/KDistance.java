// Given a dic and a target word, find all words in the dic that have at most k
// edit distance from target. a delete/insert/change operation is 1 edit distance.

import java.util.*;
// idea: use DP to compare words. dp[i][j] is the edit distance of w1[0,i) and w2[0,j), then
// if w1.charAt(i-1) == w2.charAt(j-1): dp[i][j] = dp[i-1][j-1]; if w1.charAt(i-1) != w2.charAt(j-1):
// dp[i][j] = Math, min(dp[i-1][j-1],dp[i][j-1],dp[i-1][j]) + 1
// compare all the strings with the word needs O(N*L^2) Time, we can use Trie + DFS to accelerate
public class KDistance {
	class TrieNode {
		TrieNode[] children;
        boolean isWord;
        int[] dp;
        TrieNode(int size) {
            children = new TrieNode[26];
            isWord = false;
            dp = new int[size+1];
        }
	}
	
	TrieNode root;
    List<String> result;
    public void add(String word, int len) {
        TrieNode pos = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (pos.children[c-'a'] == null) {
                pos.children[c-'a'] = new TrieNode(len);
            }
            pos = pos.children[c-'a'];
        }
        pos.isWord = true;
    }

    public List<String> kDistance(String word, String[] dic, int k) {
        result = new ArrayList<String>();
        if (dic.length == 0) return result;
        root = new TrieNode(word.length());
        for(String w:dic) {
            add(w, word.length());
        }
        int[] initial = new int[word.length()+1];
        for(int i = 0; i < initial.length; i++) {
            initial[i] = i;
        }
        dfs(1, root, "", word, k, initial);
        return result;
    }
    
    public void dfs(int depth, TrieNode root, String temString, String word, int k, int[] last) {
        if(root.isWord && root.dp[word.length()] <= k) {
            result.add(temString);
        }
        for(int j = 0; j < 26; j++) {
            if(root.children[j] != null) {
                char c = (char) (j + 'a');
                TrieNode tem = root.children[j];
                tem.dp[0] = depth;
                for (int i = 1; i <= word.length(); i++) {
                    if(word.charAt(i - 1) == c) {
                        tem.dp[i] = last[i-1];
                    }
                    else {
                        tem.dp[i] = Math.min(Math.min(last[i-1], tem.dp[i-1]), last[i]) + 1;
                    }
                }
                String nn = temString + c;
                dfs(depth + 1, tem, nn, word, k, tem.dp);
            }
        }
    }

	public static void main(String[] args) {
		KDistance s = new KDistance();
        String[] dic = {"as","dfs","asdg","asf","m","mmm"};
        List<String> res = s.kDistance("as",dic, 2);
        for(String sm:res) {
            System.out.println(sm);
        }
	}

}


//public class EditDistance {
//public int getDistance(String a, String b) {
//	if(a.length() == 0) return b.length();
//	if(b.length() == 0) return a.length();
//	int dp[][] = new int[a.length()+1][b.length()+1];
//	for(int i = 0; i <= a.length(); i++) {
//		dp[i][0] = i;
//	}
//	for(int j = 0; j <= b.length(); j++) {
//		dp[0][j] = j;
//	}
//	for(int i = 1; i <= a.length(); i++) {
//		for(int j = 1; j <= b.length(); j++) {
//			if(a.charAt(i-1) == b.charAt(j-1)) {
//				dp[i][j] = dp[i-1][j-1];
//			}
//			else {
//				dp[i][j] = 1 + Math.min(dp[i][j-1], (Math.min(dp[i-1][j], dp[i-1][j-1])));
//			}
//		}
//	}
//	return dp[a.length()][b.length()];
//}
//
//public static void main(String[] args) {
//	EditDistance ed = new EditDistance();
//	System.out.println(ed.getDistance("asd","dfs"));
//}
//}
