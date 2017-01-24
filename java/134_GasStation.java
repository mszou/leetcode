/**
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
 * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
 * Note:
 * The solution is guaranteed to be unique.
 */

public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // idea: Greedy, traverse from one point, when the tank is empty, reset and restart from next station
        // sum up gas[i] and cost[i] to compute the total gas and cost. can complete iff. sumGas >= sumCost
        // O(n) Time, O(1) Space.
        int sumGas = 0, sumCost = 0;
        int start = 0, tank = 0;
        for (int i = 0; i < gas.length; i++) {
        	sumGas += gas[i];
        	sumCost += cost[i];
        	tank += gas[i] - cost[i];
        	if (tank < 0) {	// starting from this start point cannot traverse the circuit
        		start = i + 1;	// start from i + 1
        		tank = 0;	// and reset gas tank
        	}
        }
        if (sumGas < sumCost) {
        	return -1;
        } else {
        	return start;
        }
    }
}