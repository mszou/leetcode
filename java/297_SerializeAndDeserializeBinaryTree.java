/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 * For example, you may serialize the following tree
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 * as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
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
	// idea: Serialize: print the tree in preorder traversal, split nodes with ',' and 
	// use '#' to denote the null node; Deserialize: use a queue to store the preorder,
    // take values to make nodes and then DFS. a node followed by two '#'s is a leaf.
	
	// // the notation for spliter and null node can be customized as well
    // private static final String spliter = ",";
	// private static final String nullNode = "#";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        sb.deleteCharAt(sb.length() - 1);   // remove the last ','
        return sb.toString();
    }

    private void serialize(TreeNode node, StringBuilder sb) {
    	if (node == null) {    // use '#' to indicate null node
    		sb.append("#").append(",");
    	} else {
    		sb.append(node.val).append(",");  // pre-order traversal
    		serialize(node.left, sb);
    		serialize(node.right, sb);
    	}
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<String>();
        queue.addAll(Arrays.asList(data.split(",")));
        return buildTree(queue);
    }

    private TreeNode buildTree(Queue<String> queue) {
    	String val = queue.poll();
    	if (val.equals("#")) {
    		return null;
    	} else {
    		TreeNode curr = new TreeNode(Integer.valueOf(val));
    		curr.left = buildTree(queue);
    		curr.right = buildTree(queue);
    		return curr;
    	}
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));