/**
 * Follow up for "Remove Duplicates":
 * What if duplicates are allowed at most twice?
 * For example,
 * Given sorted array nums = [1,1,1,2,2,3],
 * Your function should return length = 5, with the first five elements of nums 
 * being 1, 1, 2, 2 and 3. It doesn't matter what you leave beyond the new length.
 */

// follow up: duplicates are allowed at most k times

public class Solution {
	// duplicates are allowed at most k times
	public static int k = 2;	// modify the value of k according to the requirements

    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = 0;	// i is the index to add next eligible number
        for (int n : nums) {
            if (i < k || n > nums[i - k]) {	// find the next eligible number to add
                nums[i] = n;
                i++;
            }
        }
        return i;
    }
}