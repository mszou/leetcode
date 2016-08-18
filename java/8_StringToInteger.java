/**
 * Implement atoi to convert a string to an integer.
 * Hint: Carefully consider all possible input cases.
 *
 * Requirements for atoi:
 * 1. The function first discards as many whitespace characters as necessary until the 
 *  first non-whitespace character is found. Then, starting from this character, takes an 
 *  optional initial plus or minus sign followed by as many numerical digits as possible,
 *  and interprets them as a numerical value.
 *
 * 2. The string can contain additional characters after those that form the integral
 *  number, which are ignored and have no effect on the behavior of this function.
 *
 * 3. If the first sequence of non-whitespace characters in str is not a valid integral
 *  number, or if no such sequence exists because either str is empty or it contains only
 *  whitespace characters, no conversion is performed.
 *
 * 4. If no valid conversion could be performed, a zero value is returned. If the correct
 *  value is out of the range of representable values, INT_MAX (2147483647) or
 *  INT_MIN (-2147483648) is returned.
 */

public class Solution {
    public int myAtoi(String str) {
        if (str == null || str.length() == 0) {
        	return 0;
        }
        str = str.trim();	// disgard whitespace characters
        Boolean neg = false;
        int index = 0;
        // deal with the sign
        if (str.charAt(index) == '-') {
        	neg = true;
        	index++;
        } else if (str.charAt(index) == '+') {
        	index++;
        }
        long num = 0;
        for (; index < str.length(); index++) {
        	char c = str.charAt(index);
        	if (c < '0' || c > '9') {
        		break;
        	}
        	num = num * 10 + (c - '0');
        	if (num > Integer.MAX_VALUE) {
        		if (neg) {
        			return Integer.MIN_VALUE;
        		} else {
        			return Integer.MAX_VALUE;
        		}
        	}
        }
        if (neg) {
        	num = 0 - num;
        }
        return (int)num;
    }
}