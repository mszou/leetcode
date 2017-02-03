import java.util.Arrays;
// idea: DFS + two pointers (for two sum), O(n^(k-1)) Time for k sum.
public class NSum {
	int diff = Integer.MAX_VALUE;
	int closestSum = 0;
	
	public int kSumClosest(int[] nums, int k, int target) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		if (nums.length == 1) {
			return nums[0];
		}
		Arrays.sort(nums);
		dfs(nums, k, target, 0, 0);
		return closestSum;
	}
	
	public void dfs(int[] nums, int k, int target, int sum, int pos) {
		if (k == 2) {	// becomes two sum
			twoSum(nums, pos, nums.length - 1, target, sum);
			return;
		} else {
			for (int i = pos; i < nums.length - 2; i++) {
				dfs(nums, k - 1, target, sum + nums[i], i + 1);
			}
		}
	}

	// use two pointers for two sum
	private void twoSum(int[] nums, int start, int end, int target, int sum) {
		while (start < end) {
			int nowSum = sum + nums[start] + nums[end];
			int nowDiff = Math.abs(nowSum - target);
			if (nowDiff < diff) {	// find a closer sum
				closestSum = nowSum;
				diff = nowDiff;
			}
			if (nowSum == target) {	// find exactly the same sum with target
				return;
			} else if (nowSum < target) {
				start++;
			} else {
				end--;
			}
		}
	}

	public static void main(String[] args) {
		NSum s = new NSum();
        int[] nums = {10,12,13,16,18,21,29,24,25,26,44};
        int res = s.kSumClosest(nums, 7, 113);
        System.out.println(res);
	}

}
