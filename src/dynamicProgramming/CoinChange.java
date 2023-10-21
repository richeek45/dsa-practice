package dynamicProgramming;

import java.util.Arrays;

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


    static int findCoinChange2(int[] coins, int sum, int N, int[][] dp) {
        // dp[i][j] -> represents number of ways to arrange coins for (i number of coins, j = sum)
        // for each recursion stack we will calculate for 2 call stacks
        // one with decreasing sum and finding sol and another with decreasing number of coins
        // after calculating add the sol in the dp array with i as number of coins and j as sum dp[coins][sum]

        // if there are 0 coins no way is there
        if (sum < 0 || N == 0) {
            return 0;
        }

        // if sum = 0 return 1 ways
        if (sum == 0) {
            return dp[N][sum] =  1;
        }

        // if the ways are already calculated then return the value
        if (dp[N][sum] != -1) {
            return dp[N][sum];
        }

        return dp[N][sum] = findCoinChange2(coins, sum-coins[N-1], N, dp) + findCoinChange2(coins, sum, N-1, dp);
    }

    public static void printArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int sum = 5;
        int[] coins = {1, 2, 3};
        int N = coins.length;
        // {1, 1, 1, 1}, {1, 1, 2}, {2, 2}, {1, 3}
        int[][] dp = new int[N+1][sum+1];
        for (int[] row: dp) {
            Arrays.fill(row, -1);
        }
//        int count = findCoinChange(coins, sum, N);
        int count1 = findCoinChange2(coins, sum, N, dp);
        System.out.println(count1);
        printArray(dp);
    }
}
