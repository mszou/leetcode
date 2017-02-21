/**
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 * The update(i, val) function modifies nums by updating the element at index i to val.
 * Example:
 * Given nums = [1, 3, 5]
 * sumRange(0, 2) -> 9
 * update(1, 2)
 * sumRange(0, 2) -> 8
 * Note:
 * The array is only modifiable by the update function.
 * You may assume the number of calls to update and sumRange function is distributed evenly.
 */

// sol 1: use segment tree (~2n nodes in the tree). Build tree: O(n) Time, O(n) Space;
// update: O(h = logn) Time, O(1) Space; Range sum query: O(logn) Time, O(1) Space.
public class NumArray {
	class SegmentNode {
		int start;
		int end;
		int sum;
		SegmentNode left;
		SegmentNode right;
		public SegmentNode(int start, int end) {
			this.start = start;
			this.end = end;
			this.sum = 0;
			this.left = null;
			this.right = null;
		}
	}

	SegmentNode root;
	int[] nums;

	public NumArray(int[] nums) {
		this.nums = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			this.nums[i] = nums[i];
		}
		root = buildTree(nums, 0, nums.length - 1);
	}

	private SegmentNode buildTree(int[] nums, int start, int end) {
		if (start > end) {
			return null;
		}
		SegmentNode node = new SegmentNode(start, end);
		if (start == end) {
			node.sum = nums[start];
		} else {
			int mid = start + (end - start) / 2;
			node.left = buildTree(nums, start, mid);
			node.right = buildTree(nums, mid + 1, end);
			node.sum = node.left.sum + node.right.sum;
		}
		return node;
	}

	public void update(int i, int val) {	// update nums[i] to val
		int diff = val - nums[i];
		nums[i] = val;
		if (root != null) {
			updateNode(root, i, diff);
		}
	}

	private void updateNode(SegmentNode node, int index, int diff) {
		if (node == null || index < node.start || index > node.end) {
			return;
		}
		node.sum += diff;
		updateNode(node.left, index, diff);
		updateNode(node.right, index, diff);
	}

	public int sumRange(int i, int j) {
		return getSum(root, i, j);
	}

	private int getSum(SegmentNode node, int start, int end) {
		if (node == null || start > node.end || end < node.start) {
			return 0;
		}
		if (start <= node.start && end >= node.end) {
			return node.sum;
		}
		int left = getSum(node.left, start, end);
		int right = getSum(node.right, start, end);
		return left + right;
	}
}

// sol 2: use Binary Indexed Tree
public class NumArray {
	int[] nums;
	int[] BIT;
	int n;
	public NumArray(int[] nums) {
		this.nums = nums;
		n = nums.length;
		BIT = new int[n + 1];
		for (int i = 0; i < n; i++) {
			init(i, nums[i]);
		}
	}
	private void init(int i, int val) {
		i++;
		while (i <= n) {
			BIT[i] += val;
			i += (i & -i);
		}
	}

	public void update(int i, int val) {
		int diff = val - nums[i];
		nums[i] = val;
		init(i, diff);
	}

	public int getSum(int i) {
		int sum = 0;
		i++;
		while (i > 0) {
			sum += BIT[i];
			i -= (i & -i);
		}
		return sum;
	}

	public int sumRange(int i, int j) {
		return getSum(j) - getSum(i - 1);
	}
}


/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */