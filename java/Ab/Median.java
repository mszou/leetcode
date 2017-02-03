//	find median from large file of integers
//	其实有很多对大file的处理方法。 在interview当中，华人哥哥给出的hint是用binary search
//    首先， 我们知道对任何大的file median of any int will between INT_MIN and INT_MAX。
//    所以我们知道了upper bound 和lower bound。我们猜一下median might be "guess = lower+(upper-lower)/2"。 
//    之后我们可以验证对不对。就是扫一遍这个file，看看是不是有一半的element确实小于这个数字。
//    如果是的话，这里注意一定要返回 smallest element in the file that is larger than the guess。
//    如果有超过一半的数据小于这个guess，可想而知用binary search的方法，下一步就是移动上线到guess-1. 反之移动下线。
//    对吧。那么这个算法最多需要scan 32次fille对不？这个数字当时我有点含糊。但是现在想想应该是对的。

import java.io.*;
import java.util.*;
// idea: binary search the answer. scan at most 32 times, we assume all numbers are > 0 , otherwise
// we need to use long, in case of overflow.	O(32n) Time.
public class Median {
	// even or odd
    // testcases: 18,  24,  20,  35,  19,  23,  26,  23,  19,  20  -- 21.5
    // 15,10,12,18,14,15,8 -- 14
    // 5,5,5 -- 5
    // 1,  7,  4,  2,  3,  4－－ 3.5
    // 82,102, 75,91,89 -- 89
    
    // all the minus or plus should be long
    public int smallest = Integer.MAX_VALUE;
    public int biggest = Integer.MIN_VALUE;
    public int count = 0;
    public double findMedian(List<Integer> input) {
        ScanFirst(input);
        long left = smallest;
        long right = biggest;
        while (left < right - 1) {
            int mid = (int)(left + (right - left)/2);
            if(countSmaller(input, mid) == countBigger(input, mid)) {
                left = (long) SmallerClosest(input, mid);
                right = (long) BiggerClosest(input, mid);
                break;
            }
            else if(countSmaller(input, mid) > countBigger(input, mid)) {
                right = (long)mid;
            }
            else {
                left = (long)mid;
            }
        }
        if (count % 2 == 0) return ((double)left+(double)right)/2;
        else {
            if(countSmaller(input, (int)left) == countSmaller(input, (int)left)) return (double)left;
            else return (double)right;
        }

    }

    public int SmallerClosest(List<Integer> input, int target) {
        int diff = Integer.MAX_VALUE;
        int small = Integer.MIN_VALUE;
        for(int it : input) {
            if(it <= target && (long)target - (long)it < diff) {
                diff = target - it;
                small = it;
            }
        }
        return small;
    }

    public int BiggerClosest(List<Integer> input, int target) {
        int diff = Integer.MAX_VALUE;
        int big = Integer.MAX_VALUE;
        for(int it : input) {
            if(it >= target && (long)it - (long)target < diff) {
                diff = it - target;
                big = it;
            }
        }
        return big;
    }
    
    public void ScanFirst(List<Integer> input) {
        for(int i : input) {
            if(i < smallest) smallest = i;
            if(i > biggest) biggest = i;
            count++;
        }
    }

    public int countSmaller(List<Integer> input, int target) {
        int c = 0;
        for(int i : input) {
            if(i < target) {
                c++;
            }
        }
        return c;
    }

    public int countBigger(List<Integer> input, int target) {
        int c = 0;
        for(int i : input) {
            if(i > target) {
                c++;
            }
        }
        return c;
    }

	public static void main(String[] args) {
		Median s = new Median();
        List<Integer> input = new ArrayList<Integer>(Arrays.asList(18,  24,  20,  35,  19,  23,  26,  23,  19,  20));
        System.out.println(s.findMedian(input));
	}

}
