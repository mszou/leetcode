/**
 * Given a roman numeral, convert it to an integer.
 * Input is guaranteed to be within the range from 1 to 3999.
 */

// Roman: I(1), V(5), X(10), L(50), C(100), D(500), M(1000)
// Only I,X,C can be placed on the left of one-magnitude-greater units, and only once

 public class Solution {
    public int romanToInt(String s) {
    	// idea: use a HashMap to store the Roman characters and their corresponding value
    	// Compute the value from lower digits to higher digits, if a smaller unit occurs before
        // a larger unit, we should subtract its value from the res; otherwise, add it to the res
    	if (s == null || s.length() == 0) {
    		return 0;
    	}
    	Map<Character, Integer> map = new HashMap<Character, Integer>();
    	map.put('I', 1);
    	map.put('V', 5);
    	map.put('X', 10);
    	map.put('L', 50);
    	map.put('C', 100);
    	map.put('D', 500);
    	map.put('M', 1000);

    	int len = s.length();
    	int res = map.get(s.charAt(len - 1));  // first get the lowest digit
    	for (int i = len - 2; i >= 0; i--) {   // compute from right to left
    		// check whether the previous letter is a smaller or larger unit
            if (map.get(s.charAt(i+1)) <= map.get(s.charAt(i))) {
    			res += map.get(s.charAt(i));
    		} else {  // small unit before large unit
    			res -= map.get(s.charAt(i));
    		}
    	}
    	return res;
    }
 }