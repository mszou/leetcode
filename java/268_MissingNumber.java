/**
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 * For example,
 * Given nums = [0, 1, 3] return 2.
 * Note:
 * Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
 */

public class Solution {
    public int missingNumber(int[] nums) {
        // idea: do swaps to put each num on the corresponding pos (number i is stored in nums[i]) except
        // for number n, then traverse to find the missing (unmatched) number.   O(n) Time, O(1) Space.
        int n = nums.length, i = 0;
        while (i < n) {
            while (nums[i] != i && nums[i] < n) {   // ignore number n because no index n
                int t = nums[i];
                nums[i] = nums[t];
                nums[t] = t;
            }
            i++;
        }
        for (i = 0; i < n; i++) {
            if (nums[i] != i) { // unmatch, means no number i in the array
                return i;
            }
        }
        return n;
    }
}