/**
 * Implement the following operations of a queue using stacks.
 * push(x) -- Push element x to the back of queue.
 * pop() -- Removes the element from in front of queue.
 * peek() -- Get the front element.
 * empty() -- Return whether the queue is empty.
 * Notes:
 * You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is empty operations are valid.
 * Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque (double-ended queue), as long as you use only standard operations of a stack.
 * You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).
 */

class MyQueue {
	Stack<Integer> stack1;	// input, stack order, LIFO
	Stack<Integer> stack2;	// output, queue order, FIFO

	public MyQueue() {
		stack1 = new Stack<Integer>();
		stack2 = new Stack<Integer>();
	}

	private void s1ToS2() {
		while (!stack1.empty()) {
			stack2.push(stack1.pop());
		}
	}

	// Push element x to the back of queue.
	public void push(int x) {
		stack1.push(x);
	}

	// Removes the elements from in front of queue.
	public void pop() {
		if (stack2.empty()) {
			this.s1ToS2();
		}
		stack2.pop();
	}

	// Get the front element.
	public int peek() {
		if (stack2.empty()) {
			this.s1ToS2();
		}
		return stack2.peek();
	}

	// Return whether the queue is empty.
	public boolean empty() {
		return stack1.empty() && stack2.empty();
	}
}