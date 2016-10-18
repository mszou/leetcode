/**
 * Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) 
 * of elements in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.
 * If there are multiple solutions, return any subset is fine.
 *
 * Example 1:
 * nums: [1,2,3]
 * Result: [1,2] (of course, [1,3] will also be ok)
 *
 * Example 2:
 * nums: [1,2,4,8]
 * Result: [1,2,4,8]
 */

public class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        // idea: DP, track max subset and pre-index (to form a linked list). O(n^2) Time, O(n) Space.
        int len = nums.length;
        Arrays.sort(nums);
        int[] count = new int[len];	// count[i] stores # of elements in the subset with nums[i] as the largest element
        int[] pre = new int[len];	// pre[i] is the original index of the previous element in the subset
        Arrays.fill(count, 1);	// initialize, count is at least one for itself
        Arrays.fill(pre, -1);	// initialize, assign -1 if no previous element
        int max = 0, index = -1;    // the # elements in the larest divisible subset so far and the index for largest number
        for (int i = 0; i < len; i++) {
        	for (int j = i - 1; j >= 0; j--) {
        		if (nums[i] % nums[j] == 0) {
        			if (1 + count[j] > count[i]) {	// get more elements through this path (via nums[j])
        				count[i] = count[j] + 1;
        				pre[i] = j;
        			}
        		}
        	}
        	if (count[i] > max) {  // update max and the corresponding index
        		max = count[i];
        		index = i;
        	}
        }
        List<Integer> res = new ArrayList<>();
        // trace back, insert elements
        while (index != -1) {
        	res.add(0, nums[index]);
        	index = pre[index];
        }
        return res;
    }
}