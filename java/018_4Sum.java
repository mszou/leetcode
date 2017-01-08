/**
 * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? 
 * Find all unique quadruplets in the array which gives the sum of target.
 * Note: The solution set must not contain duplicate quadruplets.
 * For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.
 * A solution set is:
 * [[-1,  0, 0, 1], [-2, -1, 1, 2], [-2,  0, 0, 2]]
 */

public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        // idea: first sort the array, fix n1 & n2, use two pointers to find pairs of n3, n4, s.t.
        // sum = target. then move pointer of n2, and pointer of n1.  O(n^3) Time, O(1) Space.
        // remember to skip duplicates when moving the pointers
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int n1 = 0; n1 < nums.length - 3; n1++) {	// nums[n1] is the 1st integer
            if (n1 != 0 && nums[n1] == nums[n1 - 1]) {   // skip duplicates
                continue;
            }
            for (int n2 = n1 + 1; n2 < nums.length - 2; n2++) {	// nums[n2] is the 2nd integer
                if (n2 != n1 + 1 && nums[n2] == nums[n2 - 1]) {   // skip duplicates
                    continue;
                }
                // two pointers n3 & n4, from two ends of the rest numbers
                int n3 = n2 + 1;	// nums[n3] is the 3rd integer
                int n4 = nums.length - 1;	// nums[n4] is the 4th integer
                while (n3 < n4) {
                    int sum = nums[n1] + nums[n2] + nums[n3] + nums[n4];
                    if (sum < target) {
                        n3++;
                    } else if (sum > target) {
                        n4--;
                    } else {    // find a quadruplet
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(nums[n1]);
                        list.add(nums[n2]);
                        list.add(nums[n3]);
                        list.add(nums[n4]);
                        res.add(list);
                        n3++;
                        n4--;
                        while (n3 < n4 && nums[n3] == nums[n3-1]) {	// skip duplicates
                            n3++;
                        }
                        while (n3 < n4 && nums[n4] == nums[n4+1]) {	// skip duplicates
                            n4--;
                        }
                    }
                }
            }
        }
        return res;
    }
}