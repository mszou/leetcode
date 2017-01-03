/**
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 * Example:
 * Given nums = [-2, 0, 3, -5, 2, -1]
 * sumRange(0, 2) -> 1
 * sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3
 * Note:
 * 1. You may assume that the array does not change.
 * 2. There are many calls to sumRange function.
 */


public class NumArray {
	// idea: use an array to store the accumulative sums from nums[0], if the range doesn't start from
    // beginning, sumRange(i,j) = sums[j]-sum[i-1]. O(n) Time for initialization, O(1) query, O(n) Space.
    // To save space, can also use nums array to store sums, and original nums can be got from subtraction.
	int[] sums;

    public NumArray(int[] nums) {	// compute the accumulative sum
        if (nums.length != 0) {
            sums = new int[nums.length];
            sums[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                sums[i] = nums[i] + sums[i - 1];
            }
        }
    }

    public int sumRange(int i, int j) {
        if (i == 0) {
        	return sums[j];
        } else {
            return sums[j] - sums[i - 1];
        }
    }
}

// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.sumRange(1, 2);