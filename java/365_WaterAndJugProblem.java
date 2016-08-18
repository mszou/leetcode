/**
 * You are given two jugs with capacities x and y litres. There is an infinite amount of water supply available. 
 * You need to determine whether it is possible to measure exactly z litres using these two jugs.
 * If z liters of water is measurable, you must have z liters of water contained within one or both buckets by the end.
 * Operations allowed:
 * Fill any of the jugs completely with water.
 * Empty any of the jugs.
 * Pour water from one jug into another till the other jug is completely full or the first jug itself is empty.
 *
 * Example 1: (From the famous "Die Hard" example)
 * Input: x = 3, y = 5, z = 4
 * Output: True
 *
 * Example 2:
 * Input: x = 2, y = 6, z = 5
 * Output: False
 */

public class Solution {
    public boolean canMeasureWater(int x, int y, int z) {
        // idea: use the property of Bézout's identity and check if z is a multiple of GCD(x, y)
        // z is measurable iff. z can be expressed as z = ax + by (a,b are integers)
        // According to Bézout's identity, ax + by = i * GCD(x,y) (i is integer) and 
        // GCD(x,y) is the smallest positive integer that can be written in this form

        // limit brought by the statement that water is finallly in one or both buckets
		if (x + y < z) {
			return false;
		}
		// cases that only need fill operations (a or b is zero)
		if (x == z || y == z || x + y == z) {
			return true;
		}
		// get GCD, use the property of Bézout's identity, and check if z is a multiple of GCD(x,y)
		return z % GCD(x, y) == 0;
    }

    public int GCD(int x, int y) {
    	while (y != 0) {
    		int temp = y;
    		y = x % y;
    		x = temp;
    	}
    	return x;
    }
}