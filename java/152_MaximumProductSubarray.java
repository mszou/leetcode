/**
 * Find the contiguous subarray within an array (containing at least one number) which has the largest product.
 * For example, given the array [2,3,-2,4],
 * the contiguous subarray [2,3] has the largest product = 6.
 */

public class Solution {
    public int maxProduct(int[] nums) {
        // idea: DP. use 2 arrays: max[i] is max product so far, min[i] is min product so far (maybe negative)
        // update max[i] & min[i] according to whether nums[i] positive or negative.   O(n) Time, O(n) Space.
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int[] max = new int[len];
        int[] min = new int[len];
        min[0] = max[0] = nums[0];
        int res = nums[0];  // always record the maximum product so far
        for (int i = 1; i < len; i++) {
            min[i] = max[i] = nums[i];
            if (nums[i] > 0) {  // more considerately, use ">="
                max[i] = Math.max(max[i], max[i - 1] * nums[i]);
                min[i] = Math.min(min[i], min[i - 1] * nums[i]);
            } else if (nums[i] < 0) {
                max[i] = Math.max(max[i], min[i - 1] * nums[i]);
                min[i] = Math.min(min[i], max[i - 1] * nums[i]);
            } // else(nums[i] == 0), then min[i] & max[i] should both be 0, no need to change
            res = Math.max(res, max[i]);
        }
        return res;
    }
}