/**
 * Given a list of unique words. Find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
 * Example 1:
 * Given words = ["bat", "tab", "cat"]
 * Return [[0, 1], [1, 0]]
 * The palindromes are ["battab", "tabbat"]
 * Example 2:
 * Given words = ["abcd", "dcba", "lls", "s", "sssll"]
 * Return [[0, 1], [1, 0], [3, 2], [2, 4]]
 * The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
 */

public class Solution {
	/**
	 * @param words An array of Strings representing unique words
	 * @return all pairs of distinct indices s.t. concatenation of the two words is a palindrome
	 */
    public List<List<Integer>> palindromePairs(String[] words) {
        // sol 1: naive. For each word, go through the array and check whether the concatenated
        // string is palindromic or not.    O(n^2*k) Time (k is the average length of each word)
        
        // sol 2: For each word, try separating it into two parts, if one part is palindromic,
        // then check whether the reverse of the other part is another word in the given list.
        // use HashMap<word, index> to store index of each word.    O(n*k^2) Time, O(n) Space.
        List<List<Integer>> res = new ArrayList<>();
        if (words == null || words.length < 2) {
            return res;
        }
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);	// put all words into map
        }
        // for words[i], split it at j, and check in which part the axis can be
        for (int i = 0; i < words.length; i++) {    
            for (int j = 0; j <= words[i].length(); j++) {    // "<=" because "" needs to be considered
                String str1 = words[i].substring(0, j);
                String str2 = words[i].substring(j);
                if (isPalindrome(str1)) {   // axis can be the middle of str1
                    String str2rvs = new StringBuilder(str2).reverse().toString();
                    if (map.getOrDefault(str2rvs, i) != i) {    // map contains str2rvs and is another word
                        res.add(Arrays.asList(map.get(str2rvs), i));
                    }
                }
                if (isPalindrome(str2) && str2.length() != 0) {   // avoid duplicate in processing the other word first
                    String str1rvs = new StringBuilder(str1).reverse().toString();
                    if (map.getOrDefault(str1rvs, i) != i) {    // map contains str1rvs and is another word
                        res.add(Arrays.asList(i, map.get(str1rvs)));
                    }
                }
            }
        }
        return res;
    }
    
    public boolean isPalindrome(String str) {
        int left = 0, right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left++) != str.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}