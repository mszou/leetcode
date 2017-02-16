/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
 * For example, 
 * Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6 (6 units of rain water are being trapped).
 */

public class Solution {
    public int trap(int[] height) {
        if (height == null || height.length <= 2) {
            return 0;
        }
        // sol 1: One-pass, add by column, use two pointers from left and right, choose the
        // lower one as bound, move that pointer to next bar (height h). If h <= bound, it
        // can trap (bound - h) water; if h > bound, compare it with the other pointer and
        // update the bound. Repeat this until two pointers meet.  O(n) Time, O(1) Space.
        int left = 0, right = height.length - 1;
        int smaller, sum = 0;	// smaller is the current smaller bound
        while (left < right) {
            if (height[left] < height[right]) {
                smaller = height[left];
                while (left < right && height[left] <= smaller) {
                    sum += smaller - height[left];
                    left++;
                }
            } else {
                smaller = height[right];
                while (left < right && height[right] <= smaller) {
                    sum += smaller - height[right];
                    right--;
                }
            }
        }
        return sum;
        
        // sol 2: divide & conquer. first find the highest bar, then the problem becomes
        // two sub-problems: to process bars on its left and bars on its right.
    }
}