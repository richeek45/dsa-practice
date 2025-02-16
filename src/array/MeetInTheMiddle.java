package array;

import java.util.Arrays;

// Given a set of n integers where n <= 40.
// Each of them is at most 1012, determine the maximum sum subset having sum less than or equal S where S <= 1018.

public class MeetInTheMiddle {

    static long[] X = new long[2000005];
    static long[] Y = new long[2000005];

    static void calcSubArray(long[] arr, long[] x, int n, int c) {
        // find the sum of all the subsets
        for (int i = 0; i < (1 << n); i++) {
            long s = 0;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    s += arr[j + c];
                }
                x[i] = s;
            }
        }
    }

    static long solveSubSetSum(long[] arr, long S) {
        int n = arr.length;
        calcSubArray(arr, X, n/2, 0);
        calcSubArray(arr, Y, n - n/2, n/2);

        int size_X = 1 << (n/2);
        int size_Y = 1 << (n - n/2);

        Arrays.sort(Y, 0, size_Y);

        long max = 0;

        for (int i = 0; i < size_X; i++) {

            if (X[i] <= S) {
                int p = lowerBound(Y, S - X[i], size_Y);

                if (p == size_Y || Y[p] != S - X[i]) {
                    p--;
                }

                max = Math.max(max, Y[p] + X[i]);

            }
        }

        return max;
    }

    static int lowerBound(long[] arr, long val, int size_Y) {
        int l = -1, r = size_Y;
        while(l + 1 < r) {
            int m = (l + r) >>> 1; // unsigned right shift to middle
            if (arr[m] >= val) {
                r = m;
            } else {
                l = m;
            }
        }
        return r;
    }

    public static void main(String[] args) {
        long[] arr = { 3, 34, 4, 12, 5, 2 };
        long S = 10;
        long max = solveSubSetSum(arr, S);
        System.out.println(max);
    }
}
