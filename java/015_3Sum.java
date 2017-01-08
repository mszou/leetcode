/**
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
 * Find all unique triplets in the array which gives the sum of zero.
 * Note: The solution set must not contain duplicate triplets.
 * For example, given array S = [-1, 0, 1, 2, -1, -4],
 * A solution set is:
 * [[-1, 0, 1], [-1, -1, 2]]
 */

public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
    	// idea: sort the array, then for each fixed num1, use two pointers to find pairs (num2, num3) s.t. sum = 0
        // O(n^2) Time, Space depends on sorting, no extra space.
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return res;
        }
        Arrays.sort(nums);
        for (int idx1 = 0; idx1 < nums.length - 2; idx1++) {
            // if the smallest number in the triplet is positive, the sum can never be 0
            if (nums[idx1] > 0) {
                break;
            }
            // skip duplicate
            if (idx1 > 0 && nums[idx1] == nums[idx1-1]) {
                continue;
            }
            // two pointers from the two ends of the rest numbers
            int idx2 = idx1 + 1;
            int idx3 = nums.length - 1;
            while (idx2 < idx3) {
                if (nums[idx1] + nums[idx2] + nums[idx3] == 0) {    // find a triplet
                    res.add(Arrays.asList(nums[idx1], nums[idx2], nums[idx3]));
                    idx2++;
                    idx3--;
                    // skip duplicate(s)
                    while (idx2 < idx3 && nums[idx2] == nums[idx2 - 1]) {
                        idx2++;
                    }
                    while (idx2 < idx3 && nums[idx3] == nums[idx3 + 1]) {
                        idx3--;
                    }
                } else if (nums[idx1] + nums[idx2] + nums[idx3] < 0) {
                    idx2++;
                    while (idx2 < idx3 && nums[idx2] == nums[idx2 - 1]) {
                        idx2++;
                    }
                } else {
                    idx3--;
                    while (idx2 < idx3 && nums[idx3] == nums[idx3 + 1]) {
                        idx3--;
                    }
                }
            }
        }
        return res;
    }
}