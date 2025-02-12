package array;

import java.util.Arrays;

public class MinJumpGame {

    static int minJumpRecur(int i, int[] arr) {
        // minJumps(start) = 1 + Min(minJumps(k)) for all k reachable from start.
        if (i >= arr.length -1) {
            return 0;
        }
        // for each iteration -> make recursions until it reaches the end and returns 0 from last element
        // since no jump can be made from the last element
        // then it goes backwards and returns 1 + last move.
        int ans = Integer.MAX_VALUE;

        for (int j = i + 1; j <= i + arr[i] && j < arr.length; j++) {
            int val = minJumpRecur(j, arr); // this wil return 0 for last element
            if (val != Integer.MAX_VALUE) {
                ans = Math.min(ans, 1 + val);
            }
        }

        return ans;

    }

    static void minJump(int[] arr) {
        int len = arr.length;
        int[] memo = new int[len];
        Arrays.fill(memo, -1);
        int minJumps = minJumpRecur(0, arr);
        int minJumpsMemo = minJumpMemo(0, arr, memo);
        System.out.println(minJumpsMemo);

    }

    static int minJumpMemo(int i, int[] arr, int[] memo) {
        if (i >= arr.length - 1) {
            return 0;
        }

        if (memo[i] != -1) {
            return memo[i];
        }

        int ans = Integer.MAX_VALUE;
        for (int j = i + 1; j <= i + arr[i] && j < arr.length; j++) {
            int val = minJumpMemo(j, arr, memo);
            if (val != Integer.MAX_VALUE) {
                ans = Math.min(ans, 1 + val);
            }
        }

        return memo[i] = ans;
    }

    static void minJumpTabular(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[n-1] = 0; // steps to reach last index, when already in last index = 0
        // calculating backwards -> bottom up tabular method
        for (int i = n-2; i >= 0; i--) {
            // finding min for each steps in range i to i + arr[i]

            for (int j = i; j <= i + arr[i] && j < n; j++) {
                // for index j we search the next arr[j] steps and each element in dp should have a min value
                // already present there and we want to find the min steps required from them by adding +1
                if (dp[j] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], 1 + dp[j]);
                }
            }
        }

        System.out.println(dp[0]);
    }

    public static void main(String[] args) {
        int[] arr = { 1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9 };
        int[] arr1 = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
//        minJump(arr1);
        minJumpTabular(arr);
    }
}
