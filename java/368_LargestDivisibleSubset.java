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
        // idea: DP. first sort nums, then use two arrays 'count' and 'pre', count[i] is the max size of subset end
        // with nums[i], pre[i] is the index (in nums) of the previous num in this subset (used for reconstruct res).
        // Traverse the array, for num[i], check previous nums that can divide it, update max count[i] & pre[i] if
        // find a longer path. Track max subset and pre-index (to form a linked list).   O(n^2) Time, O(n) Space.
        int len = nums.length;
        Arrays.sort(nums);
        int[] count = new int[len];
        int[] pre = new int[len];
        Arrays.fill(count, 1);	// initialize, count is at least one for itself
        Arrays.fill(pre, -1);	// initialize, assign -1 if no previous element
        int max = 0, index = -1;    // size of the largest divisible subset, the index of last number in it
        for (int i = 0; i < len; i++) {
        	for (int j = i - 1; j >= 0; j--) { // check nums before current nums[i]
        		if (nums[i] % nums[j] == 0) { // divisible
        			if (1 + count[j] > count[i]) {	// get more elements through this path (via nums[j])
        				count[i] = count[j] + 1;
        				pre[i] = j;
        			}
        		}
        	}
        	if (count[i] > max) {  // update max and the corresponding index of last number
        		max = count[i];
        		index = i;
        	}
        }
        List<Integer> res = new LinkedList<>();
        while (index != -1) {   // trace back, insert elements
        	res.add(0, nums[index]);
        	index = pre[index];
        }
        return res;
    }
}