/**
 * Write a program to find the n-th ugly number.
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
 * Note that 1 is typically treated as an ugly number.
 * Hint:
 * 1. The naive approach is to call isUgly for every number until you reach the n-th one. Most numbers are not ugly. Try to focus your effort on generating only the ugly ones.
 * 2. An ugly number must be multiplied by either 2, 3, or 5 from a smaller ugly number.
 * 3. The key is how to maintain the order of the ugly numbers. Try a similar approach of merging from three sorted lists: L1, L2, and L3.
 * 4. Assume you have U_k, the k-th ugly number. Then U_(k+1) must be Min(L1 * 2, L2 * 3, L3 * 5).
 */

public class Solution {
    public int nthUglyNumber(int n) {
    	// idea: DP. An ugly number must be product of either 2, 3, or 5 and a smaller ugly number.
        // Use pointers p2,p3,p5, pointing to the ugly numbers that should be multiplied next. Then
        // next ugly number is min(uglys[p2]*2, uglys[p3]*3, uglys[p5]*5).  O(n) Time, O(n) Space.
        int[] uglys = new int[n];   // array of ugly numbers
        uglys[0] = 1;   // 1 is the first ugly number
        int p2 = 0, p3 = 0, p5 = 0;	// pointers for three lists, initialized at 0, i.e. 1 is the next to be multiplied by all of them
        for (int i = 1; i < n; i++) {
            int lastNumber = uglys[i - 1];
            // move the pointers
            while (uglys[p2] * 2 <= lastNumber) {
            	p2++;
            }
            while (uglys[p3] * 3 <= lastNumber) {
            	p3++;
            }
            while (uglys[p5] * 5 <= lastNumber) {
            	p5++;
            }            
            uglys[i] = Math.min(Math.min(uglys[p2] * 2, uglys[p3] * 3), uglys[p5] * 5);
        }
        return uglys[n - 1];
    }
}