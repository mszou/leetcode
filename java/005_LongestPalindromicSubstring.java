/**
 * Given a string S, find the longest palindromic substring in S. You may assume that the 
 * maximum length of S is 1000, and there exists one unique longest palindromic substring.
 * Example:
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example:
 * Input: "cbbd"
 * Output: "bb"
 */

public class Solution {
    public String longestPalindrome(String s) {
    	// idea: find the longest palindromic substring centering at each position (i for 
        // odd-length substrings, i & i + 1 for even-length substrings) and record the longest
        // O(n^2) Time, O(n) Space.
        if (s == null || s.length() <= 1) {
            return s;
        }
        String res = "";
        for (int i = 0; i < s.length() - 1; i++) {  // for each position as center
            // Find the longest palindromic substring with the center at i (odd length)
            String temp = longest(s, i, i);
            if (temp.length() > res.length()) {
                res = temp;
            }
            // palindromic substring of with even length requires two same center characters
            if (s.charAt(i) == s.charAt(i + 1)) {
                temp = longest(s, i, i + 1);
                if (temp.length() > res.length()) {
                    res = temp;
                }
            }
        }
        return res;
    }
    
    // returns the longest substring centering at (bigin, end) (begin <= end)
    private String longest(String s, int begin, int end) {
        while (begin >= 0 && end <= s.length() - 1 && s.charAt(begin) == s.charAt(end)) {
            begin--;
            end++;
        }
        return s.substring(begin + 1, end);
    }
}