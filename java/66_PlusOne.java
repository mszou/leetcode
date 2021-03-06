/**
 * Given a non-negative number represented as an array of digits, plus one to the number.
 * The digits are stored such that the most significant digit is at the head of the list.
 */


 public class Solution {
    public int[] plusOne(int[] digits) {
        // The complexity is O(1)
        // computation: f(n) = 9/10 + 1/10 * O(n-1)  (probability of carry = 1/10)
        //  ==>  O(n) =  10 / 9 = 1.1111 = O(1)
        // idea: add one from the least significant digit
        if (digits == null || digits.length == 0) {
            return null;
        }
        int n = digits.length;
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i] ++;	// plus one directly
                return digits;
            } else {
                digits[i] = 0;
            }
        }
        int[] res = new int[n+1];
        res[0] = 1;
        return res;
    }
}