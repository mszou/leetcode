/**
 * Count the number of prime numbers less than a non-negative number, n.
 */

// naive idea: Let's start with a isPrime function. To determine if a number is prime, 
// we need to check if it is not divisible by any number less than n. 
// Time complexity of isPrime(): O(n). so counting the primes up to n would be O(n^2).
// The "Sieve of Eratosthenes" is one of the most efficient ways to find all prime numbers up to n.

public class Solution {
    public int countPrimes(int n) {
        // idea: instead of finding primes, we eliminate composite numbers. first set all true,
        // then eliminate multiples of 2, multiples of 3, etc until multiples of sqrt(n).
        // The "Sieve of Eratosthenes" uses an extra O(n) memory and its runtime complexity is O(n log log n)
        boolean[] isPrime = new boolean[n]; // isPrime[i] is true if i is a prime number
        // initialization, first set them all true
        for (int i = 2; i < n; i++) {	// because 0 and 1 are not primes
            isPrime[i] = true;  
        }
        // ending condition is i * i < n instead of i < sqrt(n) to avoid repeatedly calling expensive sqrt()
        for (int i = 2; i * i < n; i++) {
            if (!isPrime[i]) {  // already eliminated by its divisor
                continue;
            }
            for (int j = i * i; j < n; j += i) {    // eliminate the multiples of i from i^2
                isPrime[j] = false;
            }
        }
        // count the primes
        int count = 0;
        for (boolean num : isPrime) {
            if (num) {
                count++;
            }
        }
        return count;
    }
}