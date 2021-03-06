/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * For example, given s = "aab",
 * Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
 */

public class Solution {
    public int minCut(String s) {
        // idea: DP. cut[i] is the minimum cuts for s.substring(i). O(n^2) Time, O(n) Space
        // consider odd length palindrome and even length palindrome separately
        int n = s.length();
        int[] cut = new int[n+1];
        for (int i = 0; i <= n; i++) {
        	cut[i] = i - 1;	// initialization: cut into every single letter
        }
        for (int i = 0; i < n; i++) {
        	// odd length palindrome, from i-j to i+j
        	for (int j = 0; i - j >= 0 && i + j < n && s.charAt(i-j) == s.charAt(i+j); j++) {
        		cut[i+j+1] = Math.min(cut[i+j+1], cut[i-j] + 1);
        	}
        	// even length palindrome, from i-j to i+j+1
        	for (int j = 1; i - j + 1 >= 0 && i + j < n && s.charAt(i-j+1) == s.charAt(i+j); j++) {
        		cut[i+j+1] = Math.min(cut[i+j+1], cut[i-j+1] + 1);
        	}
        }
        return cut[n];
    }
}