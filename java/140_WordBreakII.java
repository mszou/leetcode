/**
 * Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.
 * Return all such possible sentences.
 * For example, given
 * s = "catsanddog",
 * dict = ["cat", "cats", "and", "sand", "dog"].
 * A solution is ["cats and dog", "cat sand dog"].
 */

public class Solution {
	// idea: use a map<string, possible sentences>, and memorized DFS
	Map<String, List<String>> map = new HashMap<String, List<String>>();

    public List<String> wordBreak(String s, Set<String> wordDict) {
        if (map.containsKey(s)) {
    		return map.get(s);
    	}
    	List<String> res = new ArrayList<String>();
    	int n = s.length();
    	if (wordDict.contains(s)) {
    		res.add(s);
    	}
    	for (int i = 1; i <= n; i++) {
    		String curr = s.substring(i);
    		if (wordDict.contains(curr)) {
    			List<String> strs = wordBreak(s.substring(0,i), wordDict);
    			if (strs.size() != 0) {
    				for (Iterator<String> it = strs.iterator(); it.hasNext(); ) {
    					res.add(it.next() + " " + curr);
    				}
    			}
    		}
    	}
    	map.put(s, res);
    	return res;
    }
}