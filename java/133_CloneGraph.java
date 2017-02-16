/**
 * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
 * OJ's undirected graph serialization:
 * Nodes are labeled uniquely.
 * We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
 * As an example, consider the serialized graph {0,1,2#1,2#2,2}.
 * The graph has a total of three nodes, and therefore contains three parts as separated by #.
 * First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
 * Second node is labeled as 1. Connect node 1 to node 2.
 * Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
 * Visually, the graph looks like the following:
 *        1
 *       / \
 *      /   \
 *     0 --- 2
 *          / \
 *          \_/
 */

/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    // idea: use map to store the node in original graph and the corresponding node in the cloned graph
    
    // sol 1: DFS. clone the nodes and neighbors by DFS
    private HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        return clone(node);
    }

    // DFS to clone nodes and neighbors from node, returns the cloned node for 'node'
    private UndirectedGraphNode clone(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }
        if (map.containsKey(node)) {
            return map.get(node);
        }
        UndirectedGraphNode clonedNode = new UndirectedGraphNode(node.label);
        map.put(node, clonedNode);
        for (UndirectedGraphNode neighbor : node.neighbors) {
            clonedNode.neighbors.add(clone(neighbor));  // add the cloned neighbors to clonedNode
        }
        return clonedNode;
    }

    //sol 2: BFS, first clone all nodes and build the mapping, then clone edges (neighborhood)
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
        	return null;
        }
        ArrayList<UndirectedGraphNode> nodes = new ArrayList<UndirectedGraphNode>();
        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        // clone nodes, BFS, add in next nodes by checking the neighbors of existing nodes
        nodes.add(node);
        map.put(node, new UndirectedGraphNode(node.label));
        int index = 0;
        while (index < nodes.size()) {
        	UndirectedGraphNode curr = nodes.get(index);
        	for (UndirectedGraphNode neighbor : curr.neighbors) {
        		if (!map.containsKey(neighbor)) {
        			map.put(neighbor, new UndirectedGraphNode(neighbor.label));
        			nodes.add(neighbor);
        		}
        	}
        	index++;
        }
        // clone neighbors (edges), build neighborhood in the cloned graph
        for (UndirectedGraphNode n : nodes) {
        	UndirectedGraphNode newNode = map.get(n);
        	for (UndirectedGraphNode nb : n.neighbors) {
        		newNode.neighbors.add(map.get(nb));
        	}
        }
        return map.get(node);
    }
}