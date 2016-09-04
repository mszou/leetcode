/**
 * Validate if a given string is numeric.
 * Some examples:
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 * Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one.
 */

 public class Solution {
    public boolean isNumber(String s) {
    	// idea: consider all possible cases for digits, dot, and exponential
        if (s == null || s.length() == 0) {
        	return false;
        }
        int start = 0, end = s.length() - 1;
        while (start <= end && Character.isWhitespace(s.charAt(start))) {
        	start++;
        }
        if (start > end) {	// all whitespaces
        	return false;
        }
        while (end >= start && Character.isWhitespace(s.charAt(end))) {
        	end--;
        }
        // skip +/- at the head
        if (s.charAt(start) == '+' || s.charAt(start) == '-') {
        	start++;
        }
        boolean isNum = false, isDot = false, isExp = false;
        int i = start;
        while (i <= end) {
        	char c = s.charAt(i);
        	if (Character.isDigit(c)) {    // 0 ~ 9
        		isNum = true;
        	} else if (c == '.') {
        		if (isDot || isExp) {	// has a dot or exponential before
        			return false;
        		}
        		isDot = true;
        	} else if (c == 'e') { // exponential
        		if (isExp || isNum == false) {
        			return false;
        		}
        		isExp = true;
        		isNum = false;	// not valid if no digit after 'e'
        	} else if (c == '+' || c == '-') {
        		if (s.charAt(i - 1) != 'e') {
        			return false;
        		}
        	} else {
        		return false;
        	}
        	i++;
        }
        return isNum;
    }
}