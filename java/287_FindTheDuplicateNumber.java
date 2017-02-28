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
    	// first ask interviewer if modification to the array or extra space is allowed. If yes, use naive solution.
        // naive sol: if modification is allowed, let nums[i] == i; if extra space allowed, put value i in array[i] or hashset.

        // idea: the range of integers is [1, n], the indexes are 0 ~ n, so traverse the elements in a specific order,
        // s.t. val of this num == index of next, i.e. visit nums[this.val] next. Integers are from 1 ~ n, so: 1. There
        // must be a cycle. 2. nums[0] must be outside the circle because no val == 0. Then we will get a "rho-shaped"
        // sequece and the duplicate num is the entry of the cycle, use two pointer to find it.    O(n) Time, O(1) Space.
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
        
        
        // // easier solution for specific case (without Note #4) i.e. the only one duplicate number repeat only once.
        // // use the sum of arithmetic progression (1~n). O(n) Time
        // int n = nums.length - 1;
        // long sum = (1 + n) * n / 2;  // sum of arithmetic progression 1 ~ n, use long to avoid overflow
        // for (int num : nums) {
        //     sum -= num;
        // }
        // return (int) -sum;
    }
}