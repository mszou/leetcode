/**
 * Related to question Excel Sheet Column Title
 * Given a column title as appear in an Excel sheet, return its corresponding column number.
 * For example:
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 */

public class Solution {
    public int titleToNumber(String s) {
        // idea: like 26-based system, compute each digit, every left shift equals to *26
        if (s == null || s.length() == 0) {
        	return 0;
        }
        int num = 0;
        for (char c : s.toCharArray()) {
        	num *= 26;
        	num += c - 'A' + 1;
        }
        return num;
    }
}