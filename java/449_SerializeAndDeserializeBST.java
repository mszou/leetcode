/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 * Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.
 * The encoded string should be as compact as possible.
 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {
// The difference from #297 for general BT is that this encoded string for BST need to be more compact.
// So do not need to denote null node, sort the array of nodes' values can get its in-order traversal
// idea: Serialize: use pre-order traversal, separated by space; Deserialize: get in-order traversal
// string by sorting, then build the BST based on in-order & pre-order traversal sequences
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
        	return null;
        }
        StringBuilder sb = new StringBuilder();
        preOrderHelper(root, sb);
        sb.deleteCharAt(sb.length() - 1);	// remove the last space
        return sb.toString();
    }

    private void preOrderHelper(TreeNode node, StringBuilder sb) {
    	if (node != null) {
    		sb.append(node.val).append(" ");
    		preOrderHelper(node.left, sb);
    		preOrderHelper(node.right, sb);
    	}
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null) {
        	return null;
        }
        String[] nodes = data.split("\\s");	// split by spaces
        int[] preOrder = new int[nodes.length];
        for (int i = 0; i < nodes.length; i++) {
        	preOrder[i] = Integer.parseInt(nodes[i]);
        }
        int[] inOrder = Arrays.copyOf(preOrder, preOrder.length);
        Arrays.sort(inOrder);
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < inOrder.length; i++) {
        	map.put(inOrder[i], i);
        }
        return buildBST(preOrder, 0, inOrder, 0, inOrder.length - 1, map);
    }

    private TreeNode buildBST(int[] preOrder, int preStart, int[] inOrder, int inStart, int inEnd, HashMap<Integer, Integer> map) {
    	if (preStart > preOrder.length - 1 || inStart > inEnd) {
    		return null;
    	}
    	TreeNode root = new TreeNode(preOrder[preStart]);
    	// find the position of current root in inOrder, then elements on its left is left subtree
    	int pos = map.get(root.val);
    	root.left = buildBST(preOrder, preStart + 1, inOrder, inStart, pos - 1, map);
    	root.right = buildBST(preOrder, preStart + pos - inStart + 1, inOrder, pos + 1, inEnd, map);
    	return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));