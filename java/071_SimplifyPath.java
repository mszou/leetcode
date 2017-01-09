/**
 * Given an absolute path for a file (Unix-style), simplify it.
 * For example,
 * path = "/home/", => "/home"
 * path = "/a/./b/../../c/", => "/c"
 * 
 * Corner Cases:
 * 1. Did you consider the case where path = "/../"?
 * In this case, you should return "/".
 * 2. Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
 * In this case, you should ignore redundant slashes and return "/home/foo".
 */

public class Solution {
    public String simplifyPath(String path) {
    	// idea: split the path from slashes, then simplify it according to different cases
        // Use a LinkedList to record the stack.    O(n) Time. O(n) Space.
    	StringBuilder sb = new StringBuilder("/"); // start with "/"
    	LinkedList<String> pathStack = new LinkedList<String>();
    	for (String s : path.split("/")) {
    		if (s.equals("..")) { // go one level above
    			if (!pathStack.isEmpty()) {
    				pathStack.removeLast(); // equivalent to pop stack
    			}
    		} else if (!s.equals("") && !s.equals(".")) { // skip "." and ""
    			pathStack.add(s);
    		}
    	}
    	for (String s : pathStack) {
    		sb.append(s + "/");   // construct string with items in the stack
    	}
    	if (!pathStack.isEmpty()) {
    		sb.setLength(sb.length() - 1);  // remove the last '/'
    	}
    	return sb.toString();
    }
}