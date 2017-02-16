/**
 * Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.
 * Return all such possible sentences.
 * For example, given
 * s = "catsanddog",
 * dict = ["cat", "cats", "and", "sand", "dog"].
 * A solution is ["cats and dog", "cat sand dog"].
 * UPDATE (2017/1/4):
 * The wordDict parameter had been changed to a list of strings (instead of a set of strings). Please reload the code definition to get the latest changes.
 */

public class Solution {
	// idea: DFS + backtracking, check suffixes to match a word, then recursively break the prefixes.
    // Optimization: 1. put wordDict into set to reduce the searching time.
    // 2. memorization, use map<string, sentences> to record the res for visited substrings
	Map<String, List<String>> map = new HashMap<>();
    Set<String> wordSet = new HashSet<>();

    public List<String> wordBreak(String s, List<String> wordDict) {
        for (String word : wordDict) {
            wordSet.add(word);
        }
        return dfs(s, wordSet);
    }

    private List<String> dfs(String s, Set<String> wordSet) {
        if (map.containsKey(s)) {
    		return map.get(s);
    	}
    	List<String> res = new ArrayList<String>();
    	int n = s.length();
    	if (wordSet.contains(s)) {
    		res.add(s);
    	}
    	for (int i = 1; i <= n; i++) {
    		String curr = s.substring(i); // check suffixes
    		if (wordSet.contains(curr)) {
    			List<String> preRes = dfs(s.substring(0,i), wordSet);
    			if (preRes.size() != 0) {  // has a solution for break
                    // use Iterator to add words to res, same as regular for loop
    				// for (Iterator<String> it = preRes.iterator(); it.hasNext(); ) {
    				// 	res.add(it.next() + " " + curr);
    				// }
                    for (String pre : preRes) {
                        res.add(pre + " " + curr);
                    }
    			}
    		}
    	}
    	map.put(s, res);
    	return res;
    }
}