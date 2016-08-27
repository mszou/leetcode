/**
 * Given two arrays, write a function to compute their intersection.
 * Example:
 * Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].
 * Note:
 * Each element in the result must be unique.
 * The result can be in any order.
 */

public class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        // sol 1: use 2 hashsets. Time: O(n)
        HashSet<Integer> set = new HashSet<Integer>();
        HashSet<Integer> interset = new HashSet<Integer>();
        for (int n1 : nums1) {	// put all numbers in nums1 into the set
            set.add(n1);
        }
        for (int n2 : nums2) {
            if (set.contains(n2)) {
                interset.add(n2);
                set.remove(n2);
            }	// note: add(), contains(), remove() all O(1) time
        }

        // // sol 2: sort the arrays and use two pointers. Time: O(nlogn)
        // Set<Integer> interset = new HashSet<>();
        // Arrays.sort(nums1);
        // Arrays.sort(nums2);
        // int i = 0, j = 0;
        // while (i < nums1.length && j < nums2.length) {
        //     if (nums1[i] < nums2[j]) {
        //         i++;
        //     } else if (nums1[i] > nums2[j]) {
        //         j++;
        //     } else {
        //         interset.add(nums1[i]);
        //         i++;
        //         j++;
        //     }
        // }
        
        // put nums in interset into the array
        int[] res = new int[interset.size()];
        int i = 0;
        for (Integer n : interset) {
            res[i++] = n;
        }
        return res;
    }
}