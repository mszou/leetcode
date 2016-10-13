/**
 * Find the contiguous subarray within an array (containing at least one number) which has the largest product.
 * For example, given the array [2,3,-2,4],
 * the contiguous subarray [2,3] has the largest product = 6.
 */

public class Solution {
    public int maxProduct(int[] nums) {
        // idea: DP. max[i] is the max product so far, min[i] is the min product so far (maybe negative)
        // update max[i] and min[i] differently according to whether nums[i] is positive or negative
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int[] max = new int[len];
        int[] min = new int[len];
        min[0] = max[0] = nums[0];
        int res = nums[0];
        for (int i = 1; i < len; i++) {
            min[i] = max[i] = nums[i];
            if (nums[i] > 0) {  // more considerately, use ">="
                max[i] = Math.max(max[i], max[i-1] * nums[i]);
                min[i] = Math.min(min[i], min[i-1] * nums[i]);
            } else if (nums[i] < 0) {
                max[i] = Math.max(max[i], min[i-1] * nums[i]);
                min[i] = Math.min(min[i], max[i-1] * nums[i]);
            }
            res = Math.max(res, max[i]);
        }
        
        return res;
    }
}