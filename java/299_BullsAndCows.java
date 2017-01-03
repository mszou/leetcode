/**
 * You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend to guess what the number is. Each time your friend makes a guess, you provide a hint that indicates how many digits in said guess match your secret number exactly in both digit and position (called "bulls") and how many digits match the secret number but locate in the wrong position (called "cows"). Your friend will use successive guesses and hints to eventually derive the secret number.
 * For example:
 * Secret number:  "1807"
 * Friend's guess: "7810"
 * Hint: 1 bull and 3 cows. (The bull is 8, the cows are 0, 1 and 7.)
 * Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B to indicate the cows. In the above example, your function should return "1A3B".
 * Please note that both secret number and friend's guess may contain duplicate digits, for example:
 * Secret number:  "1123"
 * Friend's guess: "0111"
 * In this case, the 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow, and your function should return "1A1B".
 * You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.
 */

public class Solution {
	public String getHint(String secret, String guess) {
		// idea: traverse both secret and guess, compare and count the characters
		int bulls = 0, cows = 0, total = 0;

		// sol 1: use two HashMaps to count the occurrence of each character in secret and in guess
		HashMap<Character, Integer> map1 = new HashMap<Character, Integer>();
		HashMap<Character, Integer> map2 = new HashMap<Character, Integer>();
		for (int i = 0; i < secret.length(); i++) {
			char c1 = secret.charAt(i);
			char c2 = guess.charAt(i);
			if (c1 == c2) {
				bulls++;
			}
			map1.put(c1, map1.getOrDefault(c1, 0) + 1);
			map2.put(c2, map2.getOrDefault(c2, 0) + 1);
		}
		// compute the total number of matchs regardless of position
		for (char key : map1.keySet()) {
			if (map2.containsKey(key)) {
				total += Math.min(map1.get(key), map2.get(key));
			}
		}
		cows = total - bulls;
		return bulls + "A" + cows + "B";

		// sol 2: numbers[i] is the difference of occurence of i in secret more than in guess
		int[] numbers = new int[10];
		for (int i = 0; i < secret.length(); i++) {
			int s = secret.charAt(i) - '0';
			int g = guess.charAt(i) - '0';
			if (s == g) {
				bulls++;
			} else {
				if (numbers[s] < 0) {
					cows++;
				}
				if (numbers[g] > 0) {
					cows++;
				}
				numbers[s]++;
				numbers[g]--;
			}
		}
		return bulls + "A" + cows + "B";
	}
}