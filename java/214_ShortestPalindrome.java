/**
 * Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.
 * For example:
 * Given "aacecaaa", return "aaacecaaa".
 * Given "abcd", return "dcbabcd".
 */

public class Solution {
    public String shortestPalindrome(String s) {
        if (s.length() <= 1) { 
        	return s;
        }

        // sol 1: KMP algorithm. O(n) Time, O(n) Space
        // First construct a string as (s)+(a symbol not occur in s)+(reversed s), then run KMP preprocessing
        // (to get the tracing array) on it. We want to find the longest suffix of the reversed s that matches
        // the prefix of original s, which would be the center of the shortest palindromic string. Then we just
        // need to add the reversed string of rest unmatched suffix of s in front of s to get the res.
        String longS = s + " " + new StringBuilder(s).reverse().toString();  // s doesn't contain " "
        int[] trace = new int[longS.length()];  // trace[i] is the length of matched suffix end at index i
        for (int i = 1; i < longS.length(); i++) {   // construct the trace array
            int j = trace[i - 1];
            while (j > 0 && longS.charAt(j) != longS.charAt(i)) {   // unmatch, trace back
                j = trace[j - 1];
            }
            if (longS.charAt(j) == longS.charAt(i)) {
                trace[i] = j + 1;
            }
        }
        StringBuilder res = new StringBuilder(s.substring(trace[longS.length() - 1]));  // the unmatched part
        res.reverse().append(s);
        return res.toString();

        // sol 2: two pointers, recursive, best O(n) Time, worst O(n^2) Time (case: aababababa)
        // use 2 pointers from left & right, find the first char that cannot pair with another available char in s,
        // the substring starting from here needs an additional prefix to match, then recursively construct middle.
        // key of proof: in the final result, the char at the position where j stops after the for loop
        // could neither be the middle point nor pair with aother character in the original string s.
        // detailed proof see: https://discuss.leetcode.com/topic/21068/my-7-lines-recursive-java-solution/12
        int j = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
        	if (s.charAt(i) == s.charAt(j)) {
        		j++;
        	}
        }
        if (j == s.length()) {	// s itself is palindromic
        	return s;
        }
        String suffix = s.substring(j);
        String prefix = new StringBuilder(suffix).reverse().toString();	// add prefix to match suffix
        String mid = shortestPalindrome(s.substring(0, j));	// recursively call to make mid palindromic
        return prefix + mid + suffix;
    }
}