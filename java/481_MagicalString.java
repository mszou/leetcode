/**
 * A magical string S consists of only '1' and '2' and obeys the following rules:
 * The string S is magical because concatenating the number of contiguous occurrences of characters '1' and '2' generates the string S itself.
 * The first few elements of string S is the following: S = "1221121221221121122……"
 * If we group the consecutive '1's and '2's in S, it will be:
 * 1 22 11 2 1 22 1 22 11 2 11 22 ......
 * and the occurrences of '1's or '2's in each group are:
 * 1 2	2 1 1 2 1 2 2 1 2 2 ......
 * You can see that the occurrence sequence above is the S itself.
 * Given an integer N as input, return the number of '1's in the first N number in the magical string S.
 * Note: N will not exceed 100,000.
 * Example 1:
 * Input: 6
 * Output: 3
 * Explanation: The first 6 elements of magical string S is "12211" and it contains three 1's, so return 3.
 */

public class Solution {
	public int magicalString(int n) {
		// idea: we can generate the string with the first 3 chars: 1,2,2. use two pointers:
		// 'slow' points to the num that indicates the occurance, and 'fast' points to the
		// next pos to put the new number. Use an array to store the string and keep generating
		// new numbers with 1 and 2 alternatively until fast reaches n.	O(n) Time, O(n) Space.
		if (n <= 0) {
			return 0;
		}
		if (n <= 3) {
			return 1;
		}
		int[] array = new int[n];
		array[0] = 1; array[1] = 2; array[2] = 2;
		int slow = 2, fast = 3;	// array[slow] indicate # occurance of num from the pos fast
		int num = 1, count1 = 1;
		while (fast < n) {
			for (int i = 0; i < array[slow] && fast < n; i++) {
				array[fast] = num;
				if (num == 1) {
					count1++;
				}
				fast++;
			}
			num = 3 - num;	// flip num back and forth between 1 and 2
			slow++;
		}
		return count1;
	}
}