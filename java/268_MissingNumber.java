/**
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 * For example,
 * Given nums = [0, 1, 3] return 2.
 * Note:
 * Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
 */

public class Solution {
    public int missingNumber(int[] nums) {
        // idea: do swaps to make each value equals to its index (number i is stored in nums[i])
        int n = nums.length, i = 0;
        while (i < n) {
            while (nums[i] != i && nums[i] < n) {
                int t = nums[i];
                nums[i] = nums[t];
                nums[t] = t;
            }
            i++;
        }
        for (i = 0; i < n; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return n;
    }
}