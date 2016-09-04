/**
 * Given a string S, find the longest palindromic substring in S. You may assume that the 
 * maximum length of S is 1000, and there exists one unique longest palindromic substring.
 */

public class Solution {
    public String longestPalindrome(String s) {
    	// idea: find the longest palindromic substring centering at each position (i and maybe i+0.5) and record the longest
        if (s == null || s.length() <= 1) {
            return s;
        }
        String res = "";
        for (int i = 0; i < s.length() - 1; i++) {
            // Find the longest palindromic substring with the center at i (odd length)
            String temp = longest (s, i, i);
            if (temp.length() > res.length()) {
                res = temp;
            }
            // Find the longest palindromic substring with center at i and i+1 (even length) if two characters are same
            if (s.charAt(i) == s.charAt(i + 1)) {
                temp = longest (s, i, i + 1);
                if (temp.length() > res.length()) {
                    res = temp;
                }
            }
        }
        return res;
    }
    
    public String longest(String s, int begin, int end) {	// begin <= end
        while (begin >= 0 && end <= s.length() - 1 && s.charAt(begin) == s.charAt(end)) {
            begin --;
            end ++;
        }
        return s.substring(begin + 1, end);
    }
}