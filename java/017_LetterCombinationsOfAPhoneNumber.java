/**
 * Given a digit string, return all possible letter combinations that the number could represent.
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 * Input:Digit string "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * Note:
 * Although the above answer is in lexicographical order, your answer could be in any order you want.
 */

public class Solution {
    // sol 1: recursive
    public List<String> letterCombinations(String digits) {
    	// idea: use HashMap to store the mapping from number to letters, then convert the string
        // using dfs and back-tracking. 
        List<String> res = new ArrayList<String>();
        if (digits == null || digits.length() == 0) {
            return res;
        }
        // build a look-up map<digit, letters>
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
        convert(map, digits, sb, res);
        return res;
    }
    
    // convert function: back-tracking & recursion
    private void convert(Map<Character, char[]> map, String digits, StringBuilder sb, List<String> res) {
        if (sb.length() == digits.length()) {   // finish one conversion
            res.add(sb.toString());
            return;
        }
        for (char c : map.get(digits.charAt(sb.length()))) {	// next character to convert
            sb.append(c);
            convert(map, digits, sb, res);
            sb.deleteCharAt(sb.length() - 1);   // back-tracking
        }
    }

    // sol 2: iterative. act as a queue, for each digit added, remove and copy every element in the queue and add
    // the possible letter to each element, and add them back into the queue. Repeat until all digits are converted.
    public List<String> letterCombinations(String digits) {
        LinkedList<String> res = new LinkedList<String>();
        String[] charMap = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        if (digits.length() == 0) {
            return res;
        }
        res.add("");
        for (int i = 0; i < digits.length(); i++) {
            int idx = Character.getNumericValue(digits.charAt(i));
            while (res.peek().length() == i) {
                String temp = res.remove();
                for (char c : charMap[idx].toCharArray()) {
                    res.add(temp + c);
                }
            }
        }
        return res;
    }
}