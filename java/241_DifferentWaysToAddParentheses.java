/**
 * Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.
 * Example 1
 * Input: "2-1-1".
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 * Output: [0, 2]
 * Example 2
 * Input: "2*3-4*5"
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 * Output: [-34, -14, -10, -10, 10]
 */

public class Solution {
	// idea: partition the string by operator, and recursively compute every sub-result
	// use a map<sub-string, corresponding results> to recuse duplicate computation for same substrings
	Map<String, List<Integer>> map = new HashMap<>();

    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                String p1 = input.substring(0, i);
                String p2 = input.substring(i + 1);
                List<Integer> l1 = map.getOrDefault(p1, diffWaysToCompute(p1));
                List<Integer> l2 = map.getOrDefault(p2, diffWaysToCompute(p2));
                for (Integer r1 : l1) {
                    for (Integer r2 : l2) {
                        int r = 0;
                        if (c == '+') {
                        	r = r1 + r2;
                        } else if (c == '-') {
                        	r = r1 - r2;
                        } else {
                        	r = r1 * r2;
                        }
                        res.add(r);
                    }
                }
            }
        }
        if (res.size() == 0) {	// no operator in the input string
            res.add(Integer.valueOf(input));
        }
        map.put(input, res);
        return res;
    }
}