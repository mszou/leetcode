/**
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
 * For example:
 * 1 -> A
 * 2 -> B
 * 3 -> C
 * ...
 * 26 -> Z
 * 27 -> AA
 * 28 -> AB
 */

 public class Solution {
    public String convertToTitle(int n) {
    	String res = "";
        if (n <= 0) {
        	return res;
        }
        // // recursive solution
        // return convertToTitle((n - 1) / 26) + (char)((n - 1) % 26 + 'A');
        
        // non-recursive sol
        while (n > 0) {
        	res = (char)((n - 1) % 26 + 'A') + res;
        	n = (n - 1) / 26;
        }
        return res;
    }
}