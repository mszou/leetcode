/**
 * Remember the story of Little Match Girl? By now, you know exactly what matchsticks the little match girl has, please find out a way you can make one square by using up all those matchsticks. You should not break any stick, but you can link them up, and each matchstick must be used exactly one time.
 * Your input will be several matchsticks the girl has, represented with their stick length. Your output will either be true or false, to represent whether you could make one square using all the matchsticks the little match girl has.
 * Example 1:
 * Input: [1,1,2,2,2]
 * Output: true
 * Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
 * Example 2:
 * Input: [3,3,3,3,4]
 * Output: false
 * Explanation: You cannot find a way to form a square with all the matchsticks.
 * Note:
 * The length sum of the given matchsticks is in the range of 0 to 10^9.
 * The length of the given matchstick array will not exceed 15.
 */

public class Solution {
	// idea: DFS + backtracking. Note that -* the partition problem is NP-complete *-
	// but here input is constrained not to be very large, so try DFS and backtracking
	public boolean makesquare(int[] nums) {
		if (nums == null || nums.length < 4) {
			return false;	// number of matchsticks must be at least 4
		}
		int sum = 0;
		for (int num : nums) {
			sum += num;
		}
		if (sum % 4 != 0) {
			return false;	// the sum of length must be multiple of 4
		}
		sortDes(nums);	// sort nums in descending order, good for dfs
		if (nums[0] > sum / 4) {
			return false;	// longer than the desired side length
		}
		return dfs(nums, new int[4], 0, sum / 4);
	}

	// return whether nums starting from index can make 4 sums equal to target
	private boolean dfs(int[] nums, int[] sums, int index, int target) {
		if (index == nums.length) {	// finish all nums
			if (sums[0] == target && sums[1] == target && sums[2] == target) {
				return true;	// sum can be divided into 4 same parts
			}
			return false;
		}
		for (int i = 0; i < 4; i ++) {	// try construct 4 same sub-sums
			if (sums[i] + nums[index] > target) {
				continue;
			}
			sums[i] += nums[index];
			if (dfs(nums, sums, index + 1, target)) {
				return true;
			}
			sums[i] -= nums[index];	// back-tracking
		}
		return false;
	}

	private void sortDes(int[] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (array[i] < array[j]) {
					int temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
		}
	}
}