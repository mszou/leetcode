/**
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * For example,
 * Given [3,2,1,5,6,4] and k = 2, return 5.
 * Note: 
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 */

public class Solution {
    public int findKthLargest(int[] nums, int k) {
        // // sol 1: sort then select: O(nlogn) Time + O(1) Space
        // int n = nums.length;
        // Arrays.sort(nums);
        // return nums[n - k];
        
        // // sol 2: use a priority queue which always keeps the largest k numbers so far: O(nlgk) Time + O(k) memory
        // PriorityQueue<Integer> pq = new PriorityQueue<>();
        // for (int val : nums) {
        //     pq.offer(val);
        //     if(pq.size() > k) {
        //         pq.poll();
        //     }
        // }
        // return pq.peek();
        
        // sol 3: user-defined quick select (partion method, like quick sort):
        // Time: O(n) for best case / O(n^2) for worst case, O(1) Space
        return quickSelect(nums, k - 1, 0, nums.length - 1);
    }

    // find the target element (whose index should be k in the sorted array) in the range [left, right]
    private int quickSelect(int[] arr, int k, int left, int right) {
    	// choose a pivot element (mid), put all elements > pivot to the right, < pivot to the left
        int pivot = arr[left + (right - left) / 2];
        int orgL = left, orgR = right;
        while (left <= right) {
        	// from right to left, find the first element < pivot
            while (arr[left] > pivot) {
                left++;
            }
            // from left to right, find the first element > pivot
            while (arr[right] < pivot) {
                right--;
            }
            // swap arr[left] & arr[right]
            if (left <= right) {
                swap(arr, left, right);
                left++;
                right--;
            }
        }
        // when while loop ends, it should be left == right + 1
        // if now right >= k, then k-th element should be in the left half
        if (orgL < right && k <= right) {
        	return quickSelect(arr, k, orgL, right);
        }
        // if now left <= k, then k-th element should be in the right half
        if (left < orgR && k >= left) {
        	return quickSelect(arr, k, left, orgR);
        }
        return arr[k];
    }
    
    private void swap(int[] a, int i, int j) {
        final int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
        
    /*private int partition(int[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        while(true) {
            while(i < hi && less(a[++i], a[lo]));
            while(j > lo && less(a[lo], a[--j]));
            if(i >= j) {
                break;
            }
            swap(a, i, j);
        }
        swap(a, lo, j);
        return j;
    }*/
    
    /*private boolean less(int v, int w) {
        return v < w;
    }*/
}