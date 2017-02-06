/**
 * Given an array of integers with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.
 * Note:
 * The array size can be very large. Solution that uses too much extra space will not pass the judge.
 * Example:
 * int[] nums = new int[] {1,2,3,3,3};
 * Solution solution = new Solution(nums);
 * // pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
 * solution.pick(3);
 * // pick(1) should return 0. Since in the array only nums[0] is equal to 1.
 * solution.pick(1);
 */

public class Solution {
	// sol 1: Reservoir Sampling.	O(1) init, O(1) memory, O(n) to pick.
	int[] nums;
	Random rand;

	public Solution(int[] nums) {
		this.nums = nums;
		this.rand = new Random();
	}

	public int pick(int target) {
		int res = -1;
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != target) {
				continue;
			}
			if (rand.nextInt(++count) == 0) {
				res = i;
			}
		}
		return res;
	}

	// sol 2: naive, map of index list. O(n) init, O(n) memory, O(1) pick.
	private Map<Integer, List<Integer>> indexes = new HashMap<>();
	Random rand;

	public Solution(int[] nums) {
		rand = new Random();
		for (int i = 0; i < nums.length; i++) {
			int num = nums[i];
			if (!indexes.containsKey(num)) {
				indexes.put(num, new ArrayList<Integer>());
			}
			indexes.get(num).add(i);
		}
	}

	public int pick(int target) {
		List<Integer> index = indexes.get(target);
		int i = rand.nextInt(index.size());
		return index.get(i);
	}
}