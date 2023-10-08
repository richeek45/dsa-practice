package bitManipulation;

import java.util.HashSet;

public class CountNPairCandies {
    // Given an integer N representing the number of red and blue candies and a matrix mat[][] of size N * N,
    // where mat[i][j] = 1 represents the existence of a pair between ith red candy and jth blue candy,
    // the task is to find the count of ways to select N pairs of candies
    // such that each pair contains distinct candies of different colors.
    public static int countUniquePairs(int[][] matrix, int N, int row, HashSet<Integer> blue) {
        // consider row as red and col as blue and matrix[row][col] = 1 indicates pair
        if (row == N) {
            return 1;
        }

        int count = 0;
        for(int col = 0; col < matrix.length; col++) {
            if(matrix[row][col] == 1 && !blue.contains(col)) {
                // add col to the hashset so that next time it ignores to maintain a unique pair
                blue.add(col);
                // call function for the next row
                count += countUniquePairs(matrix, N, row+1, blue);
                // removing for recursion stack
                blue.remove(col);
            }
        }

        return count;
    }

    public static int countUniquePairs2(int[][] matrix, int N) {
        // dp[i][j]: Stores count of ways to make
        // pairs between i red candies and N blue candies
        int[][] dp = new int[N+1][1<<N+1];
        // if there is no red and blue candy, the count of ways to make N pairs is 1
        dp[0][0] = 1;
        // i: stores count of red candy
        for(int i = 0; i < N; i++) {
            // permutation of N blue candies selected / not selected
            // 00 01 10 11 100 101 110 111
            for(int j = 0; j < (1<<N); j++) {
                if(dp[i][j] == 0) {
                    continue;
                }

                // iterate over k = 0 to N for matrix considering red, blue pair from matrix[i][k] = 1
                for(int k = 0; k < N; k++) {
                    // mask with kth bit as set
                    int mask = (1<<k);
                    if(((mask & j) == 0) && matrix[i][k] == 1) {
                        dp[i+1][mask | j] += dp[i][j];
                    }

                }


            }
        }

        return dp[N][(1<<N)-1];
    }

    public static void main(String[] args) {
        int N = 3;
        int[][] mat = new int[][] {
                {0, 1, 0},
                {1, 0, 0},
                {1, 1, 1}
        };
        HashSet<Integer> map = new HashSet<>();
//        int countWays = countUniquePairs(mat, N, 0, map);
        int countWays = countUniquePairs2(mat, N);
        System.out.println(countWays);

    }
}
