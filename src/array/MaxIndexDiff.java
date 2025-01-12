package array;

import java.util.Stack;

public class MaxIndexDiff {
    // https://www.geeksforgeeks.org/given-an-array-arr-find-the-maximum-j-i-such-that-arrj-arri/

    static void maxIndexDiff(int[] arr) {
        // We consider an auxiliary array : rightMax[], such that, rightMax[i] = max element of the subarray arr[i…(n-1)],
        // the largest or equal element after arr[i] element Suppose (arr[i], arr[jLast]) is a pair,
        // such that arr[jLast] is the last greater or equal element than arr[i].
        // For the pairs ending with arr[jLast] :  ( arr[k], arr[jLast] ) for all k = (i+1) to
        // jLast we don’t need to consider (jLast – k) because (jLast – i ) > (jLast – k) for all such k’s.
        // So we can skip those pairs. Traversing from left to right of both arrays : arr[] and rightMax[],
        // when we first encounter rightMax[j] < arr[i[  , we know that jLast = j-1,
        // and we can skip the pairs (arr[k], arr[jLast]) for all k = (i+1) to jLast.
        // And also rightMax[] is non increasing sequence, so all elements at right side of rightMax[j]
        // is smaller than or equal to rightMax[j]. But there may be arr[x]  after arr[i] (x > i) such that
        // arr[x] < rightMax[j] for x > i, so increment i when rightMax[j] < arr[i] is encountered.

        int n = arr.length;

        int[] maxRight = new int[n];
        maxRight[n-1] = arr[n-1];
        for (int i = n-2; i >= 0; i--) {
            maxRight[i] = Math.max(maxRight[i+1], arr[i]);
        }

        int i = 0, j = 0;
        int maxDist  = Integer.MIN_VALUE;
        while (i < n && j < n) {
            if (maxRight[j] >= arr[i]) {
                maxDist = Math.max(maxDist, (j - i));
                j++;
            } else {
                i++;
            }
        }

        System.out.println(maxDist);
    }

    static void maxIndexDiff2(int[] arr) {
         int n = arr.length;

        Stack<Integer> stack = new Stack<>();

        // pushing to the stack in a decreasing order
        for (int i = 1; i < n; i++) {
            if (stack.isEmpty() || arr[stack.peek()] > arr[i]) {
                stack.push(i);
            }
        }

        int maxDiff = Integer.MIN_VALUE;
        int i = n-1;
        while (i >= 0) {

            if (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                maxDiff = Math.max(maxDiff, (i - stack.pop()));
                continue;
            }
            i--;
        }
        System.out.println(maxDiff);
    }

    public static void main(String[] args) {
        int[] arr = { 34, 8, 10, 3, 2, 80, 30, 33, 1 };
        int[] arr2 = { 9, 2, 3, 4, 5, 6, 7, 8, 18, 0 };
//        maxIndexDiff(arr2);
        maxIndexDiff2(arr);
    }
}
