/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 * Example:
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> Returns -3.
 * minStack.pop();
 * minStack.top();      --> Returns 0.
 * minStack.getMin();   --> Returns -2.
 */


/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

public class MinStack {
    // idea: use two stacks, one stores all the numbers being pushed, and the other
    // stores the min elements: (a min element is the minimum at the time being pushed)
    // So the top of minStack is always the minimum element currently in the stack.
	private Stack<Integer> stack;
	private Stack<Integer> minStack;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<Integer>();
        minStack = new Stack<Integer>();
    }
    
    public void push(int x) {   // O(1)
        stack.push(x);
        if (minStack.empty()) {
        	minStack.push(x);
        } else {
        	if (x <= minStack.peek()) {    // x is currently the minimum
        		minStack.push(x);
        	}
        }
    }
    
    public void pop() { // O(1)
        if (stack.peek().equals(minStack.peek())) {
        	minStack.pop();    // the one to be popped is current minimum
        }
        stack.pop();
    }
    
    public int top() {  // O(1)
        return stack.peek();
    }
    
    public int getMin() {   // O(1)
        return minStack.peek();
    }
}
