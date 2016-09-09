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
        // // sol 1: KMP algorithm. O(n) Time, O(n) Space
        // // Construct (s)+(some symbol not present in s)+(reversed s), and run KMP algorithm on it. we only 
        // // care about the largest suffix of the reversed string that matches the prefix of the original string
        // // So just add the first k characters of the reversed string to the original string, where k is difference
        // // between original string size and the prefix function for the last character of the constructed string.
        // String curs = s + " " + new StringBuilder(s).reverse().toString();
        // int[] trace = new int[curs.length()];
        // for (int i = 1; i < curs.length(); i++) {
        //     int curindex = trace[i - 1];
        //     while (curindex > 0 && curs.charAt(curindex) != curs.charAt(i)) {
        //         curindex = trace[curindex - 1];
        //     }
        //     if (curs.charAt(curindex) == curs.charAt(i)) {
        //         trace[i] = curindex + 1;
        //     }
        // }
        // return new StringBuilder(s.substring(trace[curs.length()-1])).reverse().toString() + s;

        // sol 2: worst time O(n^2) (case: aababababa)
        // use 2 pointers from left & right, find the first char that cannot pair with another available char in s,
        // the substring starting from that char needs an additional prefix to match, then recursively do this.
        // key of proof: in the final result, the char at the position where j stops after the for loop
        // neither could be the mid point nor could pair with other character in the original string s.
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