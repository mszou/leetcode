/**
 * Given two arrays, write a function to compute their intersection.
 * Example:
 * Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].
 * Note:
 * Each element in the result should appear as many times as it shows in both arrays.
 * The result can be in any order.
 * Follow up:
 * What if the given array is already sorted? How would you optimize your algorithm?
 * What if nums1's size is small compared to nums2's size? Which algorithm is better?
 * What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 */

public class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        // sol 1: use hashmap. Time: O(m+n), Space: O(m) (nums1.length = m, nums2.length = n)
        // put numbers in nums1 into HashMap<number in nums1, count(difference)>. 
        // use list to store result first then transfer to array because the size is unknown.
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < nums1.length; i++) {
            if (map.containsKey(nums1[i])) {
                map.put(nums1[i], map.get(nums1[i]) + 1);
            } else {
                map.put(nums1[i], 1);
            }
        }
        for (int i = 0; i < nums2.length; i++) {
            if (map.getOrDefault(nums2[i], -1) > 0) {
                result.add(nums2[i]);
                map.put(nums2[i], map.get(nums2[i]) - 1);
            }
        }
        int[] res = new int[result.size()];
        int i = 0;
        for (Integer n : result) {
            res[i++] = n;
        }
        return res;

        // sol 2: sort arrays, and use two pointers. Time: O(mlogm + nlogn)
    }
}

// follow-up questions:
// 1. already sorted: use 2 pointers. read 2 elements from each array at a time, compare and record the intersection
// 2. put the shorter array into the hashmap to save space. Time complexity remains the same O(m+n).
// 3. If only nums2 cannot fit in memory, put all elements of nums1 into a HashMap, read chunks of array that fit into 
//  the memory, and record the intersections. If both nums1 and nums2 are so huge that neither fit into the memory, sort 
//  them individually (external sort), then read 2 elements from each array at a time in memory, record intersections.
