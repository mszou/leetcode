/**
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * Write a function to determine if a number is strobogrammatic. The number is represented as a string.
 * For example, the numbers "69", "88", and "818" are all strobogrammatic.
 */

public class Solution {
	public boolean isStrobogrammatic(String num) {
		// idea: Since only 0,1,6,8,9 are valid numbers after rotation, we can use a HashMap
		// to store the mapping then check the character pairs in corresponding positions
		HashMap<Character, Character> map = new HashMap<>();
		map.put('0', '0');
		map.put('1', '1');
		map.put('6', '9');
		map.put('8', '8');
		map.put('9', '6');
		int left = 0, right = num.length() - 1;
		while (left <= right) {
			if (!map.containsKey(num.charAt(right)) || num.charAt(left) != map.get(num.charAt(right))) {
				return false;
			}
			left++;
			right--;
		}
		return true;
	}
}