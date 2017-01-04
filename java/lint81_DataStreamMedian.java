/**
 * Numbers keep coming, return the median of numbers at every time a new number added.
 * Clarification
 * What's the definition of Median?
 * - Median is the number that in the middle of a sorted array. If there are n numbers in a sorted array A, the median is A[(n - 1) / 2]. For example, if A=[1,2,3], median is 2. If A=[1,19], median is 1.
 * Example
 * For numbers coming list: [1, 2, 3, 4, 5], return [1, 1, 2, 2, 3].
 * For numbers coming list: [4, 5, 1, 3, 2, 6, 0], return [4, 4, 4, 3, 3, 3, 3].
 * For numbers coming list: [2, 20, 100], return [2, 2, 20].
 * Challenge 
 * Total run time in O(nlogn).
 */

public class Solution {
    /**
     * @param nums: A list of integers.
     * @return: the median of numbers
     */
    // idea: use a maxHeap to store the smaller half of numbers and a minHeap to store the larger half,
    // and make sure maxHeep.size() - minHeap.size() = 0 or 1, so maxHeap.peek() is always the median
    private PriorityQueue<Integer> maxHeap, minHeap;
    private int numOfElements = 0;

    public int[] medianII(int[] nums) {
        // write your code here
        Comparator<Integer> reverseCmp = new Comparator<Integer>() {
        	@Override
        	public int compare(Integer a, Integer b) {
        		return b.compareTo(a);
        	}
        };
        int len = nums.length;
        maxHeap = new PriorityQueue<Integer>(len, reverseCmp);
        minHeap = new PriorityQueue<Integer>(len);
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
        	addNumber(nums[i]);
        	res[i] = getMedian();
        }
        return res;
    }

    private void addNumber(int num) {
    	maxHeap.add(num);
    	if (numOfElements % 2 == 0) {
    		if (minHeap.isEmpty()) {
    			numOfElements++;
    			return;
    		} else if (maxHeap.peek() > minHeap.peek()) {
    			Integer maxHeapRoot = maxHeap.poll();
    			Integer minHeapRoot = minHeap.poll();
    			maxHeap.add(minHeapRoot);
    			minHeap.add(maxHeapRoot);
    		}
    	} else {
    		minHeap.add(maxHeap.poll());
    	}
    	numOfElements++;
    }

    private int getMedian() {
    	return maxHeap.peek();
    }
}