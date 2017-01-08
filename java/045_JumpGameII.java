/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Your goal is to reach the last index in the minimum number of jumps.
 * For example:
 * Given array A = [2,3,1,1,4]
 * The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
 * Note:
 * You can assume that you can always reach the last index.
 */

public class Solution {
    public int jump(int[] nums) {
    	if (nums == null || nums.length == 0) {
            return -1;
        }

    	// // sol 1: DP. jumps[i] records the min # jumps to reach index i
    	// int len = nums.length;
    	// int[] jumps = new int[len];
    	// jumps[0] = 0;
    	// for (int i = 1; i < len; i++) {
    	// 	jumps[i] = Integer.MAX_VALUE;
    	// 	for (int j = 0; j < i; j++) {
    	// 		if (jumps[j] != Integer.MAX_VALUE && j + nums[j] >= i) {
    	// 			jumps[i] = jumps[j] + 1;	// i can be reached from j
    	// 			break;
    	// 		}
    	// 	}
    	// }
    	// return jumps[len - 1];

        // sol 2: Greedy, when traversing, find the farthest position can reach until reach the end
        // [start, end] is the range for the starting point of the jump we're currently considering
        int start = 0, end = 0, jumps = 0;
        while (end < nums.length - 1) {
            jumps++;
            int farthest = end;
            for (int i = start; i <= end; i++) {
                if (nums[i] + i > farthest) {
                    farthest = nums[i] + i;
                }
            }
            start = end + 1;
            end = farthest;
        }
        return jumps;
    }
}