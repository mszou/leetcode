/**
 * Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.
 * Example:
 * For num = 5 you should return [0,1,1,2,1,2].
 * Follow up:
 * It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
 * Space complexity should be O(n).
 * Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
 * Hint:
 * You should make use of what you have produced already.
 * Divide the numbers in ranges like [2-3], [4-7], [8-15] and so on. And try to generate new range from previous.
 * Or does the odd/even status of the number help you in calculating the number of 1s?
 */

public class Solution {
    public int[] countBits(int num) {
        // idea: DP + bit manipulation. take advantage of the periodical laws about the occurance of 1's
        int[] count = new int[num + 1];
        
        // sol 1: divide the binary form of the number into "previous digits + last digit"
        for (int i = 1; i <= num; i++) {
        	count[i] = count[i >> 1] + (i & 1);
        }
        return count;

        // // sol 2: divide the binary form of the number into "first digit + following digits"
        // int offset = 1;
        // for (int i = 1; i <= num; i++) {
        // 	if (offset * 2 == i) {	// enough for a higher level
        // 		offset *= 2;
        // 	}
        // 	count[i] = 1 + count[i - offset];
        // }
        // return count;
    }
}