// 给一浮点数组A=[x1, x2, ..., xn]，T=round(x1 + x2 + ... + xn).找一个int数组B=[y1, y2, ...., yn]，
// 其中yi = Floor(xi) 或 Ceil(xi)，s.t. y1+y2+...+yn = T，并且minimize sum |xi - yi|

// idea: 1. 先将所有的x取floor， 然后T - sum(floor(x)) 得到多少个x需要ceil，(记为m)
// 2. 按照小数部分将数组排序，从大到小取m个进行ceil，其他的floor。 最后就是想要的结果。
// sort [f1, f2, ..., fn]. Round up the largest m numbers and round down the rest, we get [y1, y2, ..., yn].
// proof: 把x_i写成整数+小数形式(i_i + f_i)，则T = sum(i1, i2, ..., in) + round(f1+f2+...+fn)。y_i可以写成i_i + R(f_i), where R(*) = 0或1。T = sum(B), 所以round(f1 + f2 + ... + fn) = R(f1)+R(f2)+...+R(fn), 如果round(f1 + f2 + ... + fn) = m, 我们需要在1~n中选出m个让R(f_i) = 1。最小化|x_i - y_i|相当于最小化|f_i - R(f_i)|
// 记得考虑正负
// Math.round(double a) returns long, Math.round(float a) returns int

import java.util.*;
public class Round {
	class MyRound {
		double diff;
		int index;
		public MyRound(double diff, int index) {
			this.diff = diff;
			this.index = index;
		}
	}
	
	public int[] smartRound(double[] input) {
		if (input == null || input.length == 0) {
			return null;
		}
		MyRound[] toRound = new MyRound[input.length];
		int[] res = new int[input.length];
		double sum = 0, floorSum = 0;
		for (int i = 0; i < input.length; i++) {
			sum += input[i];
			double floor = Math.floor(input[i]);	// Math.floor returns double
			floorSum += floor;
			toRound[i] = new MyRound(input[i] - floor, i);
			res[i] = (int)floor;
		}
		int numCeil = (int) Math.round(sum) - (int) floorSum;
		Arrays.sort(toRound, new Comparator<MyRound>(){
			public int compare(MyRound a, MyRound b) {
				if (a.diff > b.diff) {
					return -1;
				} else if (a.diff < b.diff) {
					return 1;
				} else {
					return 0;
				}
			}
		});
		// round up 'numCeil' numbers with greatest diff
		for (int i = 0; i < numCeil; i++) {
			int index = toRound[i].index;
			res[index] = res[index] + 1;
		}
		return res;
	}

	public static void main(String[] args) {
		Round s = new Round();
        double[] input = {1.5 ,1.6 ,1.7};
        int[] output = s.smartRound(input);
        for(int i : output) {
            System.out.print(i + " ");
        }
	}

}
