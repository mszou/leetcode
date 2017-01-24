/**
 * Follow up for H-Index: What if the citations array is sorted in ascending order? Could you optimize your algorithm?
 * Hint:
 * Expected runtime complexity is in O(log n) and the input is sorted.
 */

// A scientist has index h if h of his/her N papers have at least h citations each, and the other N−h papers have no more than h citations each.

public class Solution {
    public int hIndex(int[] citations) {
        // idea: if citations sorted, use Binary Search, each time check citations[mid]
        // 1. citations[mid] == len - mid = k, means there are >= k papers having >= k citations
        // 2. citations[mid] > len - mid, means no enough paper for this, so search the left half
        // 3. citations[mid] < len - mid, search the right half     O(logn) Time, O(1) Space.
        if (citations == null || citations.length == 0) {
        	return 0;
        }
        int left = 0, right = citations.length, len = citations.length;
        while (left < right) {
        	int mid = left + (right - left) / 2;
        	if (citations[mid] == len - mid) {
        		return len - mid;
        	} else if (citations[mid] < len - mid) {
        		left = mid + 1;
        	} else {
        		right = mid;
        	}
        }
        return len - left;
    }
}