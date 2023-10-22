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

    static int findCoinChange3(int[] coins, int sum, int N, int[][] dp) {
        // iterating through the loop
        // dp[N][sum] -> N = no of coins, sum -> current sum
        dp[0][0] = 1;
        for(int i = 1; i <= N; i++) {
            for (int j = 0; j <= sum; j++) {
                // no of ways without considering the last coin
                dp[i][j] += dp[i-1][j];

                // considering the current coin
                if (j - coins[i-1] >= 0) {
                    dp[i][j] += dp[i][j-coins[i-1]];
                }
            }
        }
        return dp[N][sum];
    }

    static int findCoinChange4(int[] coins, int sum, int N, int[] dp) {
        // using a 1D dp
        // taking every index of the dp as the sum and inserting ways in the dp[j] space
        dp[0] = 1; // for sum = 0, there is only 1 way
        for(int i = 0; i < N; i++) {
            // for each iteration considering coin i and index j as the sum and updating every dp[sum] in every loop
            for(int j = 1; j <= sum; j++) {
                // for every coin we need to check how to achieve the sum value of j and update dp
                int total = j - coins[i];
                if (total >= 0 && dp[total] != 0) {
                    dp[j] += dp[total];
                }
            }

            // every iteration
            for(int val : dp) {
                System.out.print(val + " ");
            }
            System.out.println();
        }

        return dp[sum];
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
        int[] dp2 = new int[sum + 1];
        int count4 = findCoinChange4(coins, sum, N, dp2);
        System.out.println(count4);
        for(int val : dp2) {
            System.out.print(val + " ");
        }
        // {1, 1, 1, 1}, {1, 1, 2}, {2, 2}, {1, 3}
//        int[][] dp = new int[N+1][sum+1];
//        int count3 = findCoinChange3(coins, sum, N, dp);
//        System.out.println(count3);
//        for (int[] row: dp) {
//            Arrays.fill(row, -1);
//        }
//        int count = findCoinChange(coins, sum, N);
//        int count2 = findCoinChange2(coins, sum, N, dp);
//        printArray(dp);
    }
}
