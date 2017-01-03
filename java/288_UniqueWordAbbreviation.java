/**
 * An abbreviation of a word follows the form <first letter><number><last letter>. Below are some examples of word abbreviations:
 * a) it                      --> it    (no abbreviation)
 *      1
 * b) d|o|g                   --> d1g
 *               1    1  1
 *      1---5----0----5--8
 * c) i|nternationalizatio|n  --> i18n
 *               1
 *      1---5----0
 * d) l|ocalizatio|n          --> l10n
 * Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.
 * Example: 
 * Given dictionary = [ "deer", "door", "cake", "card" ]
 * isUnique("dear") -> false
 * isUnique("cart") -> true
 * isUnique("cane") -> false
 * isUnique("make") -> true
 */

public class ValidWordAbbr {
	// idea: use HashMap<abbr, word> to store the given dictionary and check if an abbr is unique
	HashMap<String, String> map;

	public ValidWordAbbr(String[] dictionary) {
		map = new HashMap<String, String>();
		for (String word : dictionary) {
			String key = getKey(word);
			if (!map.containsKey(key)) {
				map.put(key, word);
			} else {
				if (!map.get(key).equals(word)) {
					map.put(key, "");	// multiple words have same key, that key would be invalid
				}
			}
		}
	}

	public boolean isUnique(String word) {
		String key = getKey(word);
		return !map.containsKey(key) || map.get(key).equals(word);
	}

	private String getKey(String word) {
		if (word.length() <= 2) {
			return word;
		}
		return word.charAt(0) + Integer.toString(word.length() - 2) + word.charAt(word.length() - 1);
	}
}