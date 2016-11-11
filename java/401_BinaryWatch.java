/**
 * A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).
 * Each LED represents a zero or one, with the least significant bit on the right.
 * For example, the above binary watch reads "3:25".
 * Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.
 * Example:
 * Input: n = 1
 * Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 * Note:
 * The order of output does not matter.
 * The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
 * The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".
 */

public class Solution {
	// sol 1: simplest, use bitCount()
	public List<String> readBinaryWatch(int num) {
		List<String> times = new ArrayList<>();
		for (int h = 0; h < 12; h++) {
			for (int m = 0; m < 60; m++) {
				if (Integer.bitCount(h) + Integer.bitCount(m) == num) {
					times.add(String.format("%d:%02d", h, m));
				}
			}
		}
		return times;
	}

	// sol 2: use backtracking
	public List<String> readBinaryWatch(int num) {
		List<String> times = new ArrayList<>();
		int[] h = new int[]{8,4,2,1};
		int[] m = new int[]{32,16,8,4,2,1};
		for (int i = 0; i <= num; i++) {
			List<Integer> hrs = generateDigit(h, i);
			List<Integer> mins = generateDigit(m, num - i);
			for (int hr : hrs) {
				if (hr >= 12) {
					continue;
				}
				for (int min : mins) {
					if (min >= 60) {
						continue;
					}
					times.add(String.format("%d:%02d", hr, min));
				}
			}
		}
		return times;
	}

	private List<Integer> generateDigit(int[] nums, int count) {
		List<Integer> res = new ArrayList<>();
		generateHelper(nums, count, 0, 0, res);
		return res;
	}

	private void generateHelper(int[] nums, int count, int pos, int sum, List<Integer> res) {
		if (count == 0) {
			res.add(sum);
			return;
		}
		for (int i = pos; i < nums.length; i++) {
			generateHelper(nums, count - 1, i + 1, sum + nums[i], res);
		}
	}

	// sol 3: exhaustive method
	String[][] hour = {{"0"},
}

	public List<String> readBinaryWatch(int num) {

	}
}