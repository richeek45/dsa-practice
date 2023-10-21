package dynamicProgramming;

public class CoinChange {
    // Given an integer array of coins[ ] of size N representing different types of denominations and an integer sum,
    // the task is to find the number of ways to make sum by using different denominations.
    //Note: Assume that you have an infinite supply of each type of coin.
    static int findCoinChange(int[] coins, int sum, int N) {
        // recursive method O(2^sum)
        // 1. Include last coin in coins in one function call
        // and don't include last coin in coins in another function call
        // 2. keep calculating remaining sum in each function call

        // If sum is 0 then there is only one solution
        if (sum == 0) {
            return 1;
        }

        // If there are no coins and sum is greater than 0, then no solution exists
        if (N <= 0 || sum < 0) {
            return 0;
        }

        return findCoinChange( coins, sum-coins[N-1], N) + findCoinChange(coins, sum, N-1);

    }

    public static void main(String[] args) {
        int sum = 5;
        int[] coins = {1, 2, 3};
        int N = coins.length;
        // {1, 1, 1, 1}, {1, 1, 2}, {2, 2}, {1, 3}
        int count = findCoinChange(coins, sum, N);
        System.out.println(count);
    }
}
