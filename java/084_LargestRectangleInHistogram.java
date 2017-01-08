/**
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
 * Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
 * The largest rectangle is shown in the shaded area, which has area = 10 unit.
 * For example,
 * Given heights = [2,1,5,6,2,3],
 * return 10.
 */

public class Solution {
	public int largestRectangleArea(int[] heights) {
		// idea: we traverse and add the bars one by one, if a bar is no shorter than the previous one, it can definitely
		// increase the rectangle area, so we need to pay attention to decreasing heights. Keep a stack to store heights 
		// when traversing from left to right. Heights in the stack are always non-descending. When we visit a bar whose
		// height <= the bar on the top of stack, pop it from stack and calculate the area with the popped bar as smallest
		// bar. The current index tells us the ‘right index’ and index of previous one in stack is the ‘left index’.
		if (heights == null || heights.length == 0) {
			return 0;
		}
		int max = 0;
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i <= heights.length; i++) {
			int curr = (i == heights.length) ? -1 : heights[i];
			while (!stack.isEmpty() && curr <= heights[stack.peek()]) {
				int h = heights[stack.pop()];
				int w = stack.isEmpty() ? i : i - stack.peek() - 1;
				max = Math.max(max, h * w);
			}
			stack.push(i);
		}
		return max;
	}
}