/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given an encoded message containing digits, determine the total number of ways to decode it.
 * For example,
 * Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
 * The number of ways decoding "12" is 2.
 */

public class Solution {
    public int numDecodings(String s) {
        // idea: DP.
        if (s == null || s.length() == 0) {
        	return 0;
        }
        int n = s.length();
        int[] ways = new int[n + 1];
        
        // version 1: from right to left. ways[i] is the number of ways to decode s.substring(i, n)
        ways[n] = 1;
        ways[n - 1] = s.charAt(n - 1) == '0' ? 0 : 1;   // base cases
        for (int i = n - 2; i >= 0; i--) {
        	if (s.charAt(i) == '0') {
        		continue;
        	} else {
        	    int num = Integer.parseInt(s.substring(i, i + 2));
        		ways[i] = (num >= 10 && num <= 26) ? ways[i + 1] + ways[i + 2] : ways[i + 1];
        	}
        }
        return ways[0];
        
        // // version 2: from left to right. ways[i] is the number of ways to decode s.substring(0, i)
        // ways[0] = 1;
        // ways[1] = s.charAt(0) == '0' ? 0 : 1;
        // for (int i = 2; i <= n; i++) {
        //     if (s.charAt(i - 1) != '0') {
        //         ways[i] = ways[i - 1];
        //     }
        //     int num = Integer.parseInt(s.substring(i - 2, i));
        //     if (num >= 10 && num <= 26) {
        //         ways[i] += ways[i - 2];
        //     }
        // }
        // return ways[n];
    }
}