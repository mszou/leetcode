//	Coding:给了一堆用户，每个用户有一堆想去的城市(用整数表示的)，这些城市是按照用户的喜好程度排序的，比如
//	Mary: 10, 2, 5, 2
//	John: 3, 10, 5, 18
//	Peter: 4, 3, 8
//	Kate: 3, 7, 18, 1
//	要求输出所有的城市，并且保持每个用户喜欢的顺序。其实就是topological sort.
// 参考LC210 course schedule II

import java.util.*;
//idea: topological sort, put every pair of adjacent cities into map
public class FavoriteCity {
	public List<Integer> getSequence(List<List<Integer>> input) {
		List<Integer> res = new ArrayList<Integer>();
		if (input.size() == 0) {
			return res;
		}
		Map<Integer, Integer> pre = new HashMap<>();
		Map<Integer, Set<Integer>> graph = new HashMap<>();
		for (List<Integer> list : input) {
			if (list == null || list.size() == 0) {
				continue;
			}
			if (list.size() == 1) {
				int single = list.get(0);
				if (!pre.containsKey(single)) {
					pre.put(single, 0);
				}
			} else {
				for (int j = 0; j < list.size() - 1; j++) {
					int first = list.get(j);
                    int second = list.get(j+1);
                    if (graph.containsKey(first)) {
                        if(graph.get(first).contains(second)) {
                            continue;
                        } else {
                            Set<Integer> tem = graph.get(first);
                            tem.add(second);
                            graph.put(first, tem);
                        }
                    } else {
                        Set<Integer> sub = new HashSet<Integer>();
                        sub.add(second);
                        graph.put(first, sub);
                    }
                    if (!pre.containsKey(first)) {
                        pre.put(first,0);
                    }
                    if (!pre.containsKey(second)) {
                        pre.put(second, 1);
                    } else {
                        pre.put(second, pre.get(second) + 1);
                    }
				}
			}
		}
		boolean hasPositive = false;
        int next = -1;
        while (true) {
            hasPositive = false;
            next = -1;
            for (Map.Entry<Integer, Integer> entry : pre.entrySet()) {
                if (entry.getValue() > 0) {
                	hasPositive = true;
                }
                if (entry.getValue() == 0) {
                    next = entry.getKey();
                    break;
                }
            }
            if (next == -1 && hasPositive) {
            	return new ArrayList<Integer>();
            }
            if (next == -1 && !hasPositive) {
            	return res;
            }
            pre.put(next, -1);
            res.add(next);
            // if the next has no children
            if (!graph.containsKey(next)) {
            	continue;
            }
            Set<Integer> children = graph.get(next);
            Iterator<Integer> it = children.iterator();
            while (it.hasNext()) {
                int following = it.next();
                if (pre.containsKey(following)) {
                    pre.put(following, pre.get(following) - 1);
                }
            }
        }
	}

	public static void main(String[] args) {
		FavoriteCity s = new FavoriteCity();
        List<List<Integer>> input = new ArrayList<List<Integer>>();
        List<Integer> t1 = new ArrayList<Integer>(Arrays.asList(10,2,5));
        List<Integer> t2 = new ArrayList<Integer>(Arrays.asList(3,10,5,18));
        List<Integer> t3 = new ArrayList<Integer>(Arrays.asList(4,3,8));
        List<Integer> t4 = new ArrayList<Integer>(Arrays.asList(3,7,18,1));
        input.add(t1);
        input.add(t2);
        input.add(t3);
        input.add(t4);
        List<Integer> res = s.getSequence(input);
        for(Integer i:res) {
            System.out.print(i + " ");
        }
	}

}
