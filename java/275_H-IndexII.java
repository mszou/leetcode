/**
 * Follow up for H-Index: What if the citations array is sorted in ascending order? Could you optimize your algorithm?
 * Hint:
 * Expected runtime complexity is in O(log n) and the input is sorted.
 */

public class Solution {
    public int hIndex(int[] citations) {
        // idea: if citations sorted, use Binary Search, each time check citations[mid]
        // 1. citations[mid] == len - mid = k, means there are >= k papers having >= k citations
        // 2. citations[mid] > len - mid, means k papers having >= citations[mid] citations, so search the left half
        // 3. citations[mid] < len - mid, search the right half
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