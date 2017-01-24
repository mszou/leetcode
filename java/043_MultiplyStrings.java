/**
 * Given two numbers represented as strings, return multiplication of the numbers as a string.
 * Note:
 * The numbers can be arbitrarily large and are non-negative.
 * Converting the input string to integer is NOT allowed.
 * You should NOT use internal library such as BigInteger.
 */

public class Solution {
    public String multiply(String num1, String num2) {
    	// idea: if lengths of two strings are m & n, then the length of product will be m+n or m+n-1.
        // Start from the lowest digit, use basic multiplication priciple for each digit: the product
        // of num1[i] & num2[j] will be placed at product[i + j](carry) & product[i + j + 1]
        // O(mn) Time, O(m + n) Space
        // * using Character.getNumericValue(c) is equivalent to 'c'-'0'
        // Note: process sign first if negative numbers are allowed, then do the same procedure.
        if (num1 == null || num1.length() == 0 || num2 == null || num2.length() == 0) {
        	return null;
        }
        int len1 = num1.length();
        int len2 = num2.length();
        // first use array to store the product because it's easy to modify
        int[] product = new int[len1 + len2];
        for (int i = len1 - 1; i >= 0; i--) {
        	for (int j = len2 - 1; j >= 0; j--) {
        		int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
        		int sum = mul + product[i + j + 1];   // add the carry from previous computations
        		product[i + j + 1] = sum % 10;
        		product[i + j] += sum / 10;
        	}
        }
        StringBuilder sb = new StringBuilder(); // convert array to string
        for (int p : product) {
        	if (sb.length() == 0 && p == 0) {
        		continue; // skip the '0's at the beginning
        	}
        	sb.append(p);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }
}