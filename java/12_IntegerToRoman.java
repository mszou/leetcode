/**
 * Given an integer, convert it to a roman numeral.
 * Input is guaranteed to be within the range from 1 to 3999.
 */

// Roman: I(1), V(5), X(10), L(50), C(100), D(500), M(1000)
// Only I,X,C can be placed on the left of one-magnitude-greater units, and only once

 public class Solution {
    public String intToRoman(int num) {
    	// idea: From large unit to small, check how many times num contains
        if (num < 1 || num > 3999) {
        	return null;
        }
        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder res = new StringBuilder();
        int digit = 0;
        while (num > 0) {
        	int times = num / nums[digit];
        	num = num % nums[digit];
        	for (; times > 0; times--) {
        		res.append(romans[digit]);
        	}
        	digit++;
        }
        return res.toString();
    }
}