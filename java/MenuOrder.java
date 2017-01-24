import java.util.*;

// idea: DFS + backtracking. Need to set a small precision of delta for double prices.
public class MenuOrder {
	class Food {
		String name;
		double price;
		public Food(String name, double price) {
			this.name = name;
			this.price = price;
		}
	}
	
	private final double precision = 0.0001;	// need to set a precision
	public List<List<String>> getMenu(Food[] menu, double total) {
		List<List<String>> res = new ArrayList<List<String>>();
		if (menu == null || menu.length == 0) {
			return res;
		}
		// sort price in ascending order, actually don't need to sort
		Arrays.sort(menu, new Comparator<Food>(){
			public int compare(Food a, Food b) {
				if (a.price > b.price) {
					return 1;
				} else if (a.price < b.price) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		dfs(res, new ArrayList<String>(), 0, menu, total, 0);
		return res;
	}
	
	private void dfs(List<List<String>> res, List<String> curr, int pos, Food[] menu, double total, double spent) {
		if (Math.abs(total - spent) <= precision) {
			res.add(new ArrayList<String>(curr));
			return;
		}
		for (int i = pos; i < menu.length; i++) {
			if (menu[i].price + spent > total) {
				return;	// here remember to return
			}
			curr.add(menu[i].name);
			dfs(res, curr, i, menu, total, spent + menu[i].price);
			curr.remove(curr.size() - 1);
		}
	}
	
	
	public static void main(String[] args) {
		MenuOrder s = new MenuOrder();
		List<List<String>> result = new ArrayList<List<String>>();
		Food[] menu =new Food[7];        
		menu[0] = s.new Food("mixed fruit", 2.15);
		menu[1] = s.new Food("french fries", 2.75);
		menu[2] = s.new Food("side salad", 3.35);
		menu[3] = s.new Food("hot wings", 3.55);
		menu[4] = s.new Food("mozza sticks", 4.20);
		menu[5] = s.new Food("sampler plate", 5.80);
		menu[6] = s.new Food("barbecue", 6.55);
		result = s.getMenu(menu, 15.05);
		for (int i = 0; i < result.size(); i++) {
			List<String> t = result.get(i);
			System.out.print("Order " + (i+1) + ": ");
			for (int k = 0; k < t.size(); k++) {
				System.out.print(t.get(k) + ", ");
			}
			System.out.println(".");
		}
	}
}
