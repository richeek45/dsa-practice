package array;

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

        for (int j = i + 1; j <= i + arr[i]; j++) {
            int val = minJumpRecur(j, arr); // this wil return 0 for last element
            if (val != Integer.MAX_VALUE) {
                ans = Math.min(ans, 1 + val);
            }
        }

        return ans;

    }

    static void minJump(int[] arr) {
        int minJumps = minJumpRecur(0, arr);
        System.out.println(minJumps);

    }

    public static void main(String[] args) {
        int[] arr = { 1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9 };
        int[] arr1 = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        minJump(arr);
    }
}
