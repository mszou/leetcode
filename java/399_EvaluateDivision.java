/**
 * Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.
 * Example:
 * Given a / b = 2.0, b / c = 3.0. 
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? . 
 * return [6.0, 0.5, -1.0, 1.0, -1.0 ].
 * The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.
 * According to the example above:
 * equations = [ ["a", "b"], ["b", "c"] ],
 * values = [2.0, 3.0],
 * queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 
 * The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
 */

public class Solution {
	// idea: consider this as a directed weighted graph, if a/b = k, then the weight of edge a->b is k,
	// b->a is 1/k, Then a query x/y is to find a path from node x to node y. use DFS to find such path.
	public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
		HashMap<String, ArrayList<String>> pairs = new HashMap<>();
		HashMap<String, ArrayList<Double>> weights = new HashMap<>();
		for (int i = 0; i < equations.length; i++) {	// initialize the graph
			String dividend = equations[i][0];
			String divisor = equations[i][1];
			if (!pairs.containsKey(dividend)) {
				pairs.put(dividend, new ArrayList<String>());
				weights.put(dividend, new ArrayList<Double>());
			}
			if (!pairs.containsKey(divisor)) {
				pairs.put(divisor, new ArrayList<String>());
				weights.put(divisor, new ArrayList<Double>());
			}
			// add edges in both directions
			pairs.get(dividend).add(divisor);
			pairs.get(divisor).add(dividend);
			weights.get(dividend).add(values[i]);
			weights.get(divisor).add(1.0 / values[i]);
		}
		double[] res = new double[queries.length];
		for (int i = 0; i < queries.length; i++) {
			String[] query = queries[i];
			res[i] = dfs(query[0], query[1], pairs, weights, new HashSet<String>(), 1.0);
			if (res[i] == 0.0) {
				res[i] = -1.0;
			}
		}
		return res;
	}

	private double dfs(String start, String end, HashMap<String, ArrayList<String>> pairs, HashMap<String, ArrayList<Double>> weights, HashSet<String> set, double value) {
		if (set.contains(start) || !pairs.containsKey(start) || !pairs.containsKey(end)) {
			return 0.0;	// already visited start or no path from current start to end
		}
		if (start.equals(end)) {	// reach the end
			return value;
		}
		set.add(start);	// put visited nodes into a set to avoid revisit
		ArrayList<String> list = pairs.get(start);
		ArrayList<Double> weightList = weights.get(start);
		double temp = 0.0;
		for (int i = 0; i < list.size(); i++) {
			temp = dfs(list.get(i), end, pairs, weights, set, value * weightList.get(i));
			if (temp != 0.0) {
				break;	// find a path to end
			}
		}
		set.remove(start);
		return temp;
	}
}