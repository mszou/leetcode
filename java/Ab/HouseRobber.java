// House robber，升级版，要返回最大值时抢的房子的index
// 变体：Airbnb的host能提供住宿的最多天数，两段住宿之间要隔开，返回最大，同LC198 house robber
// Provide a set of positive integers (an array of integers). Each integer represent number of nights user request on Airbnb.com. 
// If you are a host, you need to design and implement an algorithm to find out the maximum number a nights you can accommodate. 
// The constraint is that you have to reserve at least one day between each request, so that you have time to clean the room.
// Example:
// 1) Input: [1, 2, 3]; output: 4, because you will pick 1 and 3
// 2) input: [5,1, 2, 6]; output: 11, because you will pick 5 and 6
// 3) input: [5,1, 2, 6, 20, 2]; output: 27, because you will pick 5, 2, 20

import java.util.*;
// idea: DP
public class HouseRobber {
	public List<Integer> rob(int[] nums) {
		List<Integer> res = new ArrayList<>();
		if (nums.length == 0) {
			return res;
		}
		if (nums.length == 1) {
			res.add(0);
			return res;
		}
		if (nums.length == 2) {
			res.add(nums[0] > nums[1] ? 0 : 1);
			return res;
		}
		int[] dp = new int[nums.length];
		int[] pre = new int[nums.length];
		dp[0] = nums[0];
		dp[1] = Math.max(nums[0], nums[1]);
		for (int i = 0; i < nums.length; i++) {
			pre[i] = i;
		}
		for (int i = 2; i < nums.length; i++) {
			dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
			pre[i] = dp[i - 1] > (dp[i - 2] + nums[i]) ? i : i - 2;
		}
		int pos = nums.length - 1;
		while (pos > 1) {
			if (pre[pos] == pos) {
				pos--;
			} else {
				res.add(0, pos);
				pos = pre[pos];
			}
		}
		res.add(0, pos);
		return res;
		
	}

	public static void main(String[] args) {
		HouseRobber s = new HouseRobber();
		int[] nums = {5,7,1,2,5};
        List<Integer> res = s.rob(nums);
        for(int i : res) {
            System.out.println(i);
        }
	}

}
