/**
 * Shuffle a set of numbers without duplicates.
 * Example:
 * // Init an array with set 1, 2, and 3.
 * int[] nums = {1,2,3};
 * Solution solution = new Solution(nums);
 * // Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
 * solution.shuffle();
 * // Resets the array back to its original configuration [1,2,3].
 * solution.reset();
 * // Returns the random shuffling of array [1,2,3].
 * solution.shuffle();
 */

import java.util.*;
// idea: for each position j, we generate a random int i in [0,j] and swap nums[i] with nums[j]
// the probability of any nums[i] (i in [0,j]) to be at position j is 1/(1+j). Thus we can compute
// P(nums[0] stays at pos 0) = 1/1*(1-1/2)*(1-1/3)*...*(1-1/n) = 1/n, same to other positions.
// Then next number has 1/(n-1) probability to be placed into any un-occupied position, etc.
public class Solution {
	private int[] nums;	// the original array
	private Random rand;

    public Solution(int[] nums) {
        this.nums = nums;
        rand = new Random();
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return nums;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        if (nums == null) {
        	return null;
        }
        int[] shuffled = nums.clone();
        for (int j = 1; j < shuffled.length; j++) {
        	int i = rand.nextInt(j + 1);	// get a random int in [0,j]
        	swap(shuffled, i, j);
        }
        return shuffled;
    }

    private void swap(int[] a, int i, int j) {
    	int temp = a[i];
    	a[i] = a[j];
    	a[j] = temp;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */