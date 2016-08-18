/**
 * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 * For example,
 * Given numerator = 1, denominator = 2, return "0.5".
 * Given numerator = 2, denominator = 1, return "2".
 * Given numerator = 2, denominator = 3, return "0.(6)".
 *
 * Hint:
 * No scary math, just apply elementary math knowledge. Still remember how to perform a long division?
 * Try a long division on 4/9, the repeating part is obvious. Now try 4/333. Do you see a pattern?
 * Be wary of edge cases! List out as many test cases as you can think of and test your code thoroughly.
 */

public class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
    	// idea: first integral part, then fractional. for fractional part, use a hashmap to record 
    	// possible repeating remainders and corresponding starting indices
        if (denominator == 0) {
            return "NaN";	// not a number
        }
        if (numerator == 0) {
            return "0";
        }
        StringBuilder res = new StringBuilder();
        // append "-" if negative
        if ((numerator > 0) ^ (denominator > 0)) {  // XOR
            res.append("-");
        }
        long num = Math.abs((long)numerator);
        long den = Math.abs((long)denominator);
        
        // integral part
        res.append(num / den);
        num %= den;
        if (num == 0) { // result is an integer
            return res.toString();
        } else {
            res.append(".");
        }
        // fractional part, HashMap<remainder, start position of possible repeat numbers>
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
        while (num != 0) {
            if (map.containsKey(num)) {
                res.insert(map.get(num), "(");
                res.append(")");
                break;
            } else {
                map.put(num, res.length());
                num *= 10;
                res.append(num / den);
                num %= den;
            }
        }
        return res.toString();
    }
}