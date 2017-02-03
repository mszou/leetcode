//John's travel city: a1 a2 c2 h8 j9
//Tom's travel city: b1 a1 c3 z5
//Kate's travel city: a2 a1 h8 x8
//给你一个人john, 以及他的一堆朋友，让你计算出来和他travel的city相似度大于75%的所有朋友，并且根据这个相似度对朋友排序
//time complexity: n * m
//张三和李四的相似度 就是  张三和李四相似city/张三city总数
//李四和张三的相似度 就是  张三和李四相似city/李四city总数
//我有一个感兴趣的城市列表，我的朋友们每个人也有感兴趣的城市列表，如果朋友和我感兴趣的城市占总共他总城市个数的至少一半，就输出他的名字，要求按照相同城市个数排序。
//第二问输出的名字对应相同城市名字。楼主是hashset直接暴力的，最后被问如何优化，也没时间了。现在想想应该用bitset吧，不知道有没有更好的方法
// Similarity = intersection / union

import java.util.*;
public class Similarity {
	class Friend {
		String name;
		int common;
		List<String> cities;
		Friend(String name, int common, List<String> cities) {
			this.name = name;
			this.common = common;
			this.cities = cities;
		}
	}
	
	public List<String> getSimilarity(List<String> master, List<List<String>> friends) {
		List<String> res = new ArrayList<String>();
		if (friends.size() == 0 || master.size() == 1) {	// no friend or no travel city
			return res;
		}
		List<Friend> candidates = new ArrayList<Friend>();
		Set<String> set = new HashSet<>();
		int count = master.size() - 1;	// number of cities master traveled
		for (int i = 1; i < master.size(); i++) {
			set.add(master.get(i));
		}
		for (List<String> friend : friends) {
			String name = friend.get(0);
			int intersection = 0;
			List<String> commonCities = new ArrayList<>();
			for (int j = 1; j < friend.size(); j++) {
				if (set.contains(friend.get(j))) {
					intersection++;
					commonCities.add(friend.get(j));
				}
			}
			int union = count - intersection + friend.size() - 1;
			if (intersection * 2 >= union) {
				candidates.add(new Friend(name, intersection, commonCities));
			}
		}
		Collections.sort(candidates, new Comparator<Friend>(){
			public int compare(Friend a, Friend b) {
				return b.common - a.common;
			}
		});
		for (Friend f : candidates) {
			res.add(f.name);
			System.out.print(f.name + ": ");
			for (String city : f.cities) {
				System.out.print(city + " ");
			}
			System.out.println("");
		}
		return res;
	}

	public static void main(String[] args) {
		Similarity s = new Similarity();
        List<String> master = new ArrayList<String>();
        master.add("Me");master.add("a");master.add("b");
        master.add("c");master.add("e");master.add("d");
        List<List<String>> ff = new ArrayList<List<String>>();
        List<String> f1 = new ArrayList<String>();
        f1.add("Vivian");f1.add("a");f1.add("c");
        f1.add("d");f1.add("q");
        List<String> f2 = new ArrayList<String>();
        f2.add("David");f2.add("b");f2.add("c");
        f2.add("m");f2.add("f");f2.add("g");
        List<String> f3 = new ArrayList<String>();
        f3.add("Mike");f3.add("a");f3.add("b");
        f3.add("c");f3.add("e");f3.add("m");f3.add("n");
        ff.add(f1);
        ff.add(f2);
        ff.add(f3);
        s.getSimilarity(master, ff);
	}

}
