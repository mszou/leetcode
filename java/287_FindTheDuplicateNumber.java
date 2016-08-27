/**
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least
 * one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
 * Note:
 * 1. You must not modify the array (assume the array is read only).
 * 2. You must use only constant, O(1) extra space.
 * 3. Your runtime complexity should be less than O(n^2).
 * 4. There is only one duplicate number in the array, but it could be repeated more than once.
 */

public class Solution {
    public int findDuplicate(int[] nums) {
    	// first ask interviewer if modification to the array or extra space is allowed. If yes, use simpler solution.

    	// idea: analogy with LinkedList (O(n) time). the range of integers is [1, n-1], the indices are 0 ~ n-1,
    	// so connect the elements in a specific way, s.t. val of this num == index of next (this.next = nums[this.val])
    	// Integers are from 1 ~ n-1, so: 1. There must be a cycle. 2. nums[0] must be outside the circle
    	// then the topology we get would be a "rho-shaped" sequece with the duplicate num as the entry of the cycle
        if (nums == null || nums.length == 0) {
            return -1;
        }
        // use two pointers (fast & slow) to find the entry (x_k) of the cycle for the "rho-shaped" sequence
        //     x_0 -> x_1 -> ... x_k -> x_{k+1} ... -> x_{k+j}
        //                        ^                       |
        //                        |                       |
        //                        +-----------------------+
        // each time, fast goes 2 steps while slow goes 1 step, they will meet inside the circle
        int slow = nums[0];
        int fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }   // two pointers meet inside the loop
        // start up another "chasing" from nums[0] (proof: similar to Problem 142. Linked List Cycle II)
        fast = 0;
        while (fast != slow) {
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;
    }
}