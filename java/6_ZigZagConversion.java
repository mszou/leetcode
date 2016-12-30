/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 * (you may want to display this pattern in a fixed font for better legibility)
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 * Write the code that will take a string and make this conversion given a number of rows:
 * string convert(string text, int nRows);
 * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 */

public class Solution {
    public String convert(String s, int numRows) {
    	// idea: find the pattern of ZigZag walking. the trail has period of T = 2 * (numRows - 1)
    	// for i-th row (0 < i < numRows-1), the interval between two chars within one period is T - 2i. 
    	// write to res row by row. the first one in i-th row is the i-th character in s
        if (s == null || s.length() <= numRows || numRows == 1) {
            return s;
        }
        char[] res = new char[s.length()];
        int period = 2 * (numRows - 1);
        int count = 0;	// index in res, position to write
        // write to res row by row
        for (int i = 0; i < numRows; i++) {
            int interval = period - 2 * i;  // interval between two chars within one period
            for (int j = i; j < s.length(); j += period) {  // first char in i-th row is the i-th char in s
                res[count] = s.charAt(j);
                count++;
                // for rows in middle (0 < i < numRows-1), there are 2 characters in one period
                if (i > 0 && i < numRows - 1 && j + interval < s.length()) {
                    res[count] = s.charAt(j + interval);
                    count++;
                }
            }
        }
        return new String(res);
    }
}