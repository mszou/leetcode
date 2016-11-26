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

	// sol 3: exhaustive method, industrial approach
	String[][] hour = {{"0"},	// hours containing 0 1's
					   {"1", "2", "4", "8"},	// hours containing 1 1's
					   {"3", "5", "6", "9", "10"},	// hours containing 2 1's
					   {"7", "11"}};	// hours containing 3 1's
	String[][] minute = {{"00"},	// minutes containing 0 1's
						 {"01", "02", "04", "08", "16", "32"},	// minutes containing 1 1's
						 {"03", "05", "06", "09", "10", "12", "17", "18", "20", "24", "33", "34", "36", "40", "48"},	// minutes containing 2 1's
						 {"07", "11", "13", "14", "19", "21", "22", "25", "26", "28", "35", "37", "38", "41", "42", "44", "49", "50", "52", "56"},	// minutes containing 3 1's
						 {"15", "23", "27", "29", "30", "39", "43", "45", "46", "51", "53", "54", "57", "58"},	// minutes containing 4 1's
						 {"31", "47", "55", "59"}};	// minutes containing 5 1's

	public List<String> readBinaryWatch(int num) {
		List<Integer> times = new ArrayList<>();
		for (int i = 0; i <= 3 && i <= num; i++) {
			if (num - i <= 5) {
				for (String h : hour[i]) {
					for (String m : minute[num - i]) {
						times.add(h + ":" + m);
					}
				}
			}
		}
		return times;
	}
}