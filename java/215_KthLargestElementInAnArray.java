/**
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * For example,
 * Given [3,2,1,5,6,4] and k = 2, return 5.
 * Note: 
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 */

public class Solution {
    // // sol 1: sort then select: O(nlogn) Time + O(1) Space
    // public int findKthLargest(int[] nums, int k) {
    //     int n = nums.length;
    //     Arrays.sort(nums);
    //     return nums[n - k];
    // }
        
    // sol 2: use priority queue (min-heap) which always keeps the largest k numbers so far: 
    // O(nlogk) Time + O(k) Space
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int val : nums) {
            pq.offer(val);
            if (pq.size() > k) {    // size exceeds k
                pq.poll();  // remove the smallest in the heap
            }
        }
        return pq.peek();
    }

    // sol 3: quick select (partition method, like quick sort):
    // Time: O(n) for best case / O(n^2) for worst case, O(1) Space
    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k + 1);
    }

    // return the kth smallest element in the range [left, right]
    private int quickSelect(int[] nums, int left, int right, int k) {
        // choose a pivot element, put all elements > pivot to the right, < pivot to the left
        int pivot = nums[right];    // take the right-most number as pivot
        int i = left, j = right - 1;    // make sure nums[0, i) <= pivot, nums(j, right) > pivot
        while (i <= j) {    // compare nums[i] & pivot, move pointers until they meet
            if (nums[i] > pivot) {  // if nums[i] > pivot, swap nums[i] with nums[j], then move j
                swap(nums, i, j);
                j--;
            } else {    // if nums[i] <= pivot, move i to next num
                i++;
            }   // can be written as "if (nums[i++] > pivot) swap(nums, --i, --j);" with initially j = right
        }   // when while loop ends, it should be i == j + 1
        swap(nums, i, right);   // finally move pivot to its final place, i.e. between smaller & larger parts
        int count = i - left + 1;   // count the number of nums that < pivot from left
        if (count == k) {
            return nums[i];
        } else if (count > k) {
            return quickSelect(nums, left, i - 1, k);
        } else {
            return quickSelect(nums, i + 1, right, k - count);
        }
    }
    
    private void swap(int[] a, int i, int j) {
        final int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}