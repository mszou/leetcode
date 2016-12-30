/**
 * Determine whether an integer is a palindrome. Do this without extra space.
 * Some hints:
 * Could negative integers be palindromes? (ie, -1)
 * You cannot convert the integer to string, note the restriction of using extra space.
 * If reverse an integer, the reversed integer might overflow. How to handle such case?
 * There is a more generic way of solving this problem.
 */

public class Solution {
    public boolean isPalindrome(int x) {
    	// idea: check whether x == reverse(x), if overflow during reversal, it cannot be palindrome
        if (x < 0) {	// negative integers cannot be palindrome
        	return false;
        }
        return x == reverse(x);
    }

    public int reverse(int x) {
    	int res = 0;
    	while (x > 0) {
    		res = res * 10 + x % 10;
    		x = x / 10;
    	}
    	return res;
    }
}