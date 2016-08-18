/**
 * Given two binary strings, return their sum (also a binary string).
 * For example,
 * a = "11"
 * b = "1"
 * Return "100".
 */

 public class Solution {
    public String addBinary(String a, String b) {
    	// idea: add two binary strings from the least significant digit
        if (a == null || a.length() == 0) {
        	return b;
        }
        if (b == null || b.length() == 0) {
        	return a;
        }
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0 || carry == 1) { // for any one of these, we should do add
        	if (i >= 0) {
        		carry += a.charAt(i--) - '0';
        	}
        	if (j >= 0) {
        		carry += b.charAt(j--) - '0';
        	}
        	sb.append((char)('0' + carry % 2));
        	carry = carry / 2;
        }
        return sb.reverse().toString();
    }
}