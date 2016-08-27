/**
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). 
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, 
 * which together with x-axis forms a container, such that the container contains the most water.
 * Note: You may not slant the container.
 */

public class Solution {
    public int maxArea(int[] height) {
    	// idea: use two pointers starting from the left and right ends, and always move the shorter one
    	// every time compute the volume of the container (area of a rectangle), and record the maximum
        if (height == null || height.length < 2) {
            return 0;
        }
        int left = 0;
        int right = height.length - 1;
        int max = 0;
        while (left < right) {
            max = Math.max(max, computeArea(left, right, height));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }
    
    private int computeArea(int left, int right, int[] height) {
        return Math.min(height[left], height[right]) * (right - left);
    }
}