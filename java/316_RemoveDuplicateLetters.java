/**
 * Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.
 * Example:
 * Given "bcabc"
 * Return "abc"
 * Given "cbacdcbc"
 * Return "acdb"
 */

public class Solution {
	// sol 1: Greedy, recursive, each recursion chooses the head as small as possible. Traverse the string,
    // keep tracking of the smallest letter visited so far and the remaining count of each letter, when meet
    // the last occurance of a letter, choose the smallest letter we record as the head and remove it from
    // following substring then recursively find next letters in the rest.  O(26*n) = O(n) Time, O(1) Space.
    public String removeDuplicateLetters(String s) {
    	if (s == null || s.length() <= 1) {
    		return s;
    	}
        int[] count = new int[26];	// count the occurance of 26 letters
        for (int i = 0; i < s.length(); i++) {
        	count[s.charAt(i) - 'a']++;
        }
        int pos = 0; // the index for the smallest head for current position
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < s.charAt(pos)) {
            	pos = i;
            }
            if (--count[s.charAt(i) - 'a'] == 0) {
            	break;	// this is the last occurance for current letter, so must stop now
            }
        }
        // choose the head and remove this head letter from following substring, then recursively find next head
        return s.charAt(pos) + removeDuplicateLetters(s.substring(pos + 1).replace("" + s.charAt(pos), ""));
    }

    // sol 2: Greedy, iterative. use map<letter, last_occur_pos> to store the last occurance pos for each letter.
    // The pos of head cannot be later than the min last_occur_pos and should be the smallest letter before it.
    public String removeDuplicateLetters(String s) {
    	if (s == null || s.length() <= 1) {
    		return s;
    	}
    	Map<Character, Integer> lastPosMap = new HashMap<Character, Integer>();
    	for (int i = s.length() - 1; i >= 0; i--) {    // scan from right to left and add newly occured letters
    		if (!lastPosMap.containsKey(s.charAt(i))) {
    			lastPosMap.put(s.charAt(i), i);
    		}
    	}
    	char[] res = new char[lastPosMap.size()];
    	int begin = 0, end = findMinLastPos(lastPosMap);
    	for (int i = 0; i < res.length; i++) { // to find letter for each position
    		char minChar = 'z' + 1;   // record smallest candidate for head
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
    		lastPosMap.remove(minChar);   // minChar is already used, so remove from map
    		if (s.charAt(end) == minChar) {   // remember to update end pos if this letter is chosen
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
    	for (int pos : lastPosMap.values()) {  // find min from all values
    		minLastPos = Math.min(minLastPos, pos);
    	}
    	return minLastPos;
    }

    // sol 3: use stack, push letters to stack while popping larger ones who has later occurance.
    public String removeDuplicateLetters(String s) {
    	if (s == null || s.length() <= 1) {
    		return s;
    	}
        int[] count = new int[26];	// count the occurance of 26 letters
        boolean[] visited = new boolean[26];    // mark as true for letters in the stack
        char[] ch = s.toCharArray();
        for (char c : ch) {
        	count[c - 'a']++;
        }
        Stack<Character> stack = new Stack<Character>();	// stack for constructing res string
        for (char c : ch) {
        	count[c - 'a']--;
        	if (visited[c - 'a']) {
        		continue;
        	}
        	// if current character is smaller than stack.peek() which still has occurance later in the string
        	// it can be removed and add later. e.g a can pop b, c from stack if they occur later in the string
        	while (!stack.empty() && c < stack.peek() && count[stack.peek() - 'a'] != 0) {
        		visited[stack.pop() - 'a'] = false;
        	}
        	stack.push(c);
        	visited[c - 'a'] = true;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}