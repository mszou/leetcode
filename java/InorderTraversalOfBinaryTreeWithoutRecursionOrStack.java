/**
 * in-order traversal of binary tree without recursion or stack but using parent pointer
 */

class Node {
	int val;
	Node left, right, parent;
}

public void printInOrder(Node node) {
	if (root == null) {
		return;
	}
	// decide whether to print according to where the last visited node comes from
	Node prev = null;
	Node curr = root;
	while (curr != null) {
		// going down
		if (prev == null || prev == curr.parent) {
			if (curr.left != null) {
				prev = curr;
				curr = curr.left;
				continue;
			} else {
				System.out.print(curr.val + " ");
				if (curr.right != null) {
					prev = curr;
					curr = curr.right;
					continue;
				} else {
					prev = curr;
					curr = prev.parent;
				}
			}
		}

		// going up after left traversal
		if (curr != null && prev == curr.left) {
			System.out.print(curr.val + " ");
			if (curr.right != null) {
				prev = curr;
				curr = curr.right;
				continue;
			} else {
				prev = curr;
				curr = prev.parent;
			}
		}

		// going up after right traversal
		if (curr != null && prev == curr.right) {
			prev = curr;
			curr = prev.parent;
		}
	}
}