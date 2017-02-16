/**
 * Given a digit string, return all possible letter combinations that the number could represent.
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 * Input:Digit string "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * Note:
 * Although the above answer is in lexicographical order, your answer could be in any order you want.
 */

public class Solution {
    // the mapping from digit to letters can be stored in map or in String[]
    // sol 1: recursive, DFS + backtracking
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<String>();
        if (digits == null || digits.length() == 0) {
            return res;
        }
        Map<Character, char[]> map = new HashMap<Character, char[]>();
        map.put('0',new char[] {'0'});
        map.put('1',new char[] {'1'});
        map.put('2',new char[] {'a','b','c'});
        map.put('3',new char[] {'d','e','f'});
        map.put('4',new char[] {'g','h','i'});
        map.put('5',new char[] {'j','k','l'});
        map.put('6',new char[] {'m','n','o'});
        map.put('7',new char[] {'p','q','r','s'});
        map.put('8',new char[] {'t','u','v'});
        map.put('9',new char[] {'w','x','y','z'});
        StringBuilder sb = new StringBuilder();
        convert(map, digits, 0, sb, res);
        return res;
    }
    
    // convert function: dfs + backtracking
    private void convert(Map<Character, char[]> map, String digits, int index, StringBuilder sb, List<String> res) {
        if (index == digits.length()) {   // finish one conversion
            res.add(sb.toString());
            return;
        }
        for (char c : map.get(digits.charAt(index))) {	// next character to convert
            sb.append(c);
            convert(map, digits, index + 1, sb, res);
            sb.deleteCharAt(index);   // backtracking
        }
    }

    // sol 2: iterative. store temp res for each level. For next digit, append each possible char to
    // every previous res in the list to get res for this level. Repeat until all digits are converted.
    // note that peek() and remove() methods here are all for LinkedList, so res should be LinkedList
    public List<String> letterCombinations(String digits) {
        LinkedList<String> res = new LinkedList<String>();
        String[] charMap = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        if (digits == null || digits.length() == 0) {
            return res;
        }
        res.add("");    // remeber to add "" for initialization
        for (int i = 0; i < digits.length(); i++) {
            int idx = Character.getNumericValue(digits.charAt(i));  // aka digits.charAt(i) - '0'
            while (res.peek().length() == i) {  // for all res from previous level
                String temp = res.remove();
                for (char c : charMap[idx].toCharArray()) {
                    res.add(temp + c);
                }
            }
        }
        return res;
    }
}