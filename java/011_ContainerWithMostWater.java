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
        // O(n) Time, O(1) Space.
        if (height == null || height.length < 2) {
            return 0;
        }
        int left = 0, right = height.length - 1;
        int max = 0;
        while (left < right) {
            int area = Math.min(height[left], height[right]) * (right - left);
            max = Math.max(max, area);
            // move the shorter bar because moving longer one won't lead to a larger area
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }
}