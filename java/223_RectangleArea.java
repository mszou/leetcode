/**
 * Find the total area covered by two rectilinear rectangles in a 2D plane.
 * Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.
 * 1st rectangle: bottom left: (A, B), top right: (C, D)
 * 2nd rectangle: bottom left: (E, F), top right: (G, H)
 * Assume that the total area is never beyond the maximum possible value of int.
 */


public class Solution {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        // idea: find the four boundaries of the overlapped area, then compute total area
        int left = Math.max(A, E);
        int right = Math.max(Math.min(C, G), left);	// right = left if no overlap horizontally
        int bottom = Math.max(B, F);
        int top = Math.max(Math.min(D, H), bottom);	// top = bottom if no overlap virtically
        return (C - A) * (D - B) - (right - left) * (top - bottom) + (G - E) * (H - F);
    }
}