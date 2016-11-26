/**
 * Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.
 * Example:
 * Given "bcabc"
 * Return "abc"
 * Given "cbacdcbc"
 * Return "acdb"
 */

public class Solution {
	// sol 1: Greedy with recursion. choose the head as small as possible
    public String removeDuplicateLetters(String s) {
    	if (s == null || s.length() <= 1) {
    		return s;
    	}
        int[] count = new int[26];	// count the occurance of 26 letters
        int pos = 0; // the position for the smallest s[i] (head)
        for (int i = 0; i < s.length(); i++) {
        	count[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < s.charAt(pos)) {
            	pos = i;	// 
            }
            if (--count[s.charAt(i) - 'a'] == 0) {
            	break;	// the letter only occurs once after, so must choose
            }
        }
        return s.charAt(pos) + removeDuplicateLetters(s.substring(pos + 1).replaceAll("" + s.charAt(pos), ""));
    }

    // sol 2: Greedy without recursion (iterative).
    // first find the last appeared position for each letter, and find the smallest number in the first interval
    public String removeDuplicateLetters(String s) {
    	if (s == null || s.length() <= 1) {
    		return s;
    	}
    	// use a hashmap<letter, last position of this letter>
    	Map<Character, Integer> lastPosMap = new HashMap<Character, Integer>();
    	for (int i = s.length() - 1; i >= 0; i--) {
    		if (!lastPosMap.containsKey(s.charAt(i))) {
    			lastPosMap.put(s.charAt(i), i);
    		}
    	}
    	char[] res = new char[lastPosMap.size()];
    	int begin = 0, end = findMinLastPos(lastPosMap);
    	for (int i = 0; i < res.length; i++) {
    		char minChar = 'z' + 1;
    		// find the smallest letter before the minimal last position and take that letter
    		for (int j = begin; j <= end; j++) {
    			if (lastPosMap.containsKey(s.charAt(j)) && s.charAt(j) < minChar) {
    				minChar = s.charAt(j);
    				begin = j + 1;
    			}
    		}
    		res[i] = minChar;
    		if (i == res.length - 1) {
    			break;	// find all
    		}
    		lastPosMap.remove(minChar);
    		if (s.charAt(end) == minChar) {
    			end = findMinLastPos(lastPosMap);
    		}
    	}
    	return new String(res);
    }

    private int findMinLastPos(Map<Character, Integer> lastPosMap) {
    	if (lastPosMap == null || lastPosMap.isEmpty()) {
    		return -1;
    	}
    	int minLastPos = Integer.MAX_VALUE;
    	for (int pos : lastPosMap.values()) {
    		minLastPos = Math.min(minLastPos, pos);
    	}
    	return minLastPos;
    }

    // sol 3: use stack (or StringBuilder as a stack)
    public String removeDuplicateLetters(String s) {
    	if (s == null || s.length() <= 1) {
    		return s;
    	}
        int[] count = new int[26];	// count the occurance of 26 letters
        boolean[] visited = new boolean[26];
        char[] ch = s.toCharArray();
        for (char c : ch) {
        	count[c - 'a']++;
        }
        Stack<Character> stack = new Stack<Character>();	// answer stack
        for (char c : ch) {
        	count[c - 'a']--;
        	if (visited[c - 'a']) {
        		continue;
        	}
        	// if current character is smaller than last character in stack which occurs later in the string again
        	// it can be removed and added later. e.g stack = bc remaining string abc then a can pop b and then c
        	while (!stack.empty() && c < stack.peek() && count[stack.peek() - 'a'] != 0) {
        		visited[stack.pop() - 'a'] = false;
        	}
        	stack.push(c);
        	visited[c - 'a'] = true;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.empty()) {
        	sb.insert(0, stack.pop());
        }
        return sb.toString();
    }
}