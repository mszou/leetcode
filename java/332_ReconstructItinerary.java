/**
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.
 * Note:
 * If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 * All airports are represented by three capital letters (IATA code).
 * You may assume all tickets form at least one valid itinerary.
 * Example 1:
 * tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
 * Example 2:
 * tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
 * Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.
 */

public class Solution {
	// idea: DFS, find an Eulerian path that visits every edge exactly once.
	// use a map to store the destinations that can be reached from each airport,
	// and use a PriorityQueue to keep these destinations in lexical order

	// sol 1: rcursive, always visit the smallest destinations until nowhere can go
	Map<String, PriorityQueue<String>> canGo = new HashMap<>();
	List<String> route = new LinkedList<>();

	public List<String> findItinerary(String[][] tickets) {
		for (String[] ticket : tickets) {	// initialize the graph (Map)
			// canGo.computeIfAbsent(ticket[0], k -> new PriorityQueue()).add(ticket[1]);
			if (!canGo.containsKey(ticket[0])) {
				canGo.put(ticket[0], new PriorityQueue<String>());
			}
			canGo.get(ticket[0]).add(ticket[1]);
		}
		visit("JFK");	// itinerary begins with JFK
		return route;
	}

	private void visit(String airport) {
		while (canGo.containsKey(airport) && !canGo.get(airport).isEmpty()) {
			visit(canGo.get(airport).poll());
		}
		route.add(0, airport);	// add after child dfs finished, so add to the front
	}

	// sol 2: iterative, use a stack, push nodes while visiting, when no next, pop from stack
	public List<String> findItinerary(String[][] tickets) {
		Map<String, PriorityQueue<String>> canGo = new HashMap<>();
		for (String[] ticket : tickets) {
			if (!canGo.containsKey(ticket[0])) {
				canGo.put(ticket[0], new PriorityQueue<String>());
			}
			canGo.get(ticket[0]).add(ticket[1]);
		}
		List<String> route = new LinkedList<>();
		Stack<String> stack = new Stack<>();
		stack.push("JFK");
		while (!stack.empty()) {
			while (canGo.containsKey(stack.peek()) && !canGo.get(stack.peek()).isEmpty()) {
				stack.push(canGo.get(stack.peek()).poll());
			}
			route.add(0, stack.pop());
		}
		return route;
	}
}