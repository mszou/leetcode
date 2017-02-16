/**
 * Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.
 * Examples: 
 * "123", 6 -> ["1+2+3", "1*2*3"] 
 * "232", 8 -> ["2*3+2", "2+3*2"]
 * "105", 5 -> ["1*0+5","10-5"]
 * "00", 0 -> ["0+0", "0-0", "0*0"]
 * "3456237490", 9191 -> []
 */

public class Solution {
    public List<String> addOperators(String num, int target) {
        // idea: backtracking + dfs, consider all possible ways to add operators
        // tricks: 1.use StringBuilder to append strings; 2.convert String to char[] then process characters
        // note: 1. use long type to address overflow; 2. numbers should not start with 0
    	List<String> res = new ArrayList<>();
    	StringBuilder sb = new StringBuilder();
    	dfs(res, sb, num.toCharArray(), 0, target, 0, 0);
    	return res;
    }

    // prev is the previous result before the current point, mult is recorded for possibly upcoming multiplication
    private void dfs(List<String> res, StringBuilder sb, char[] nums, int pos, int target, long prev, long mult) {
    	if (pos == nums.length) {
    		if (target == prev) {	// check if we got a target value
    			res.add(sb.toString());
    		}
    		return;
    	}
    	long curr = 0;	// the current number (starting from index 'pos') for computation
    	for (int i = pos; i < nums.length; i++) {
    		if (nums[pos] == '0' && i != pos) {
    			break;	// non-zero numbers starting with 0 are not valid
    		}
    		curr = curr * 10 + nums[i] - '0';
    		int len = sb.length();
    		if (pos == 0) {	// for the first number, no previous num or operator before
    			dfs(res, sb.append(curr), nums, i + 1, target, curr, curr);
    			sb.setLength(len);	// backtracking
    		} else {	// try 3 operators, and backtracking
    			dfs(res, sb.append("+").append(curr), nums, i + 1, target, prev + curr, curr);
    			sb.setLength(len);
    			dfs(res, sb.append("-").append(curr), nums, i + 1, target, prev - curr, -curr);
    			sb.setLength(len);
    			dfs(res, sb.append("*").append(curr), nums, i + 1, target, prev - mult + mult * curr, mult * curr);
    			sb.setLength(len);
    		}
    	}
    }
}