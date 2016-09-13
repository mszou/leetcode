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
        // idea: backtracking + dfs, consider all possible cases to add operators
        // tricks: 1.use StringBuilder to append strings; 2.convert String to char[] then process characters
        // note: 1.use long type to address overflow; 2.numbers should not start with 0
    	List<String> res = new ArrayList<>();
    	StringBuilder sb = new StringBuilder();
    	dfs(res, sb, num.toCharArray(), 0, target, 0, 0);
    	return res;
    }

    // prev is the result for previous expression at the current point, mult is recorded for upcoming multiplication
    private void dfs(List<String> res, StringBuilder sb, char[] numChars, int pos, int target, long prev, long mult) {
    	if (pos == numChars.length) {
    		if (target == prev) {	// got a target value
    			res.add(sb.toString());
    		}
    		return;
    	}
    	long curr = 0;	// the current number in computation
    	for (int i = pos; i < numChars.length; i++) {
    		if (numChars[pos] == '0' && i != pos) {
    			break;	// non-zero numbers starting with 0 are not valid
    		}
    		curr = curr * 10 + numChars[i] - '0';
    		int len = sb.length();
    		if (pos == 0) {	// for the first number
    			dfs(res, sb.append(curr), numChars, i + 1, target, curr, curr);
    			sb.setLength(len);	// backtracking
    		} else {	// try 3 operators, and backtracking
    			dfs(res, sb.append("+").append(curr), numChars, i + 1, target, prev + curr, curr);
    			sb.setLength(len);
    			dfs(res, sb.append("-").append(curr), numChars, i + 1, target, prev - curr, -curr);
    			sb.setLength(len);
    			dfs(res, sb.append("*").append(curr), numChars, i + 1, target, prev - mult + mult * curr, mult * curr);
    			sb.setLength(len);
    		}
    	}
    }
}