import java.util.*;
import java.util.Map.Entry;

// idea: DP. dp[i][j] is the min distance to get city j with at most i flights, i.e. i-1 connections
// Give each city an index and use map to store the mapping, use another map to store the cities that
// can reach it and the corresponding distances.
public class AirTicket {
	public int getMinDis(List<String> input, String start, String end, int k) {
		if (input.size() == 0) {
			return 0;
		}
		// map each city to index, and store their neighbors and distance in the graph
		Map<String, Integer> cityToIndex = new HashMap<String, Integer>();
		Map<Integer, Set<String>> graph = new HashMap<Integer, Set<String>>();
		int count = 1;
		cityToIndex.put(start, 0);
		for (String s : input) {
			String[] parts = s.split("\\s*,\\s*");
			String startCity = parts[0].split("->")[0];
			String endCity = parts[0].split("->")[1];
			int dis = Integer.parseInt(parts[1]);
			if (!startCity.equals(start) && !cityToIndex.containsKey(startCity)) {
				cityToIndex.put(startCity, count);
				count++;
			}
			if (!cityToIndex.containsKey(endCity)) {
				cityToIndex.put(endCity, count);
				count++;
			}
			if (!graph.containsKey(cityToIndex.get(endCity))) {
				graph.put(cityToIndex.get(endCity), new HashSet<String>());	
			}
			Set<String> set = graph.get(cityToIndex.get(endCity));
			set.add(cityToIndex.get(startCity) + "," + dis);
		}
//		for (Entry<String, Integer> e : cityToIndex.entrySet()) {
//			System.out.println(e.getKey() + ":" + e.getValue());
//		}
//		for (Entry<Integer, Set<String>> e : graph.entrySet()) {
//			System.out.print(e.getKey() + ":");
//			for (String s : e.getValue()) {
//				System.out.print(s + "; ");
//			}
//			System.out.println();
//		}
		int[][] dp = new int[k + 2][count + 1];
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				dp[i][j] = Integer.MAX_VALUE;
			}
		}
		dp[0][0] = 0;
		int min = Integer.MAX_VALUE;
		int endIndex = cityToIndex.get(end);
		for (int i = 1; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				int index = j;
				if (graph.containsKey(index)) {
					Set<String> last = graph.get(index);
					Iterator<String> itr = last.iterator();
					while (itr.hasNext()) {
						String curr = itr.next();
						int sc = Integer.parseInt(curr.split(",")[0]);	// start city
						int di = Integer.parseInt(curr.split(",")[1]);	// distance
						if (dp[i - 1][sc] != Integer.MAX_VALUE) {
							// compare and choose whether transit at sc or not
							dp[i][index] = Math.min(dp[i][index], dp[i - 1][sc] + di);
						}
					}
				}
			}
			min = Math.min(min, dp[i][endIndex]);
		}
		return min;
	}

	public static void main(String[] args) {
		AirTicket a = new AirTicket();
		List<String> input = new ArrayList<>();
        input.add("c->d,3");
        input.add("a->b,2");
        input.add("b->c,5");
        input.add("a->d,10");
        input.add("d->g,1");
        input.add("g->h,1");
        input.add("d->h,3");
        input.add("a->e,1");
        input.add("e->f,7");
        input.add("f->d,1");
        System.out.println(a.getMinDis(input, "a", "d", 2));	// should return 9: a->e->f->d
	}

}
