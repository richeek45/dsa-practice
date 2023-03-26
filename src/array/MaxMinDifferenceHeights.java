package array;

import java.util.Arrays;
import java.lang.Math;

// Minimize the maximum difference between the heights
// Given the heights of N towers and a value of K, Either increase or decrease the height of every tower by K (only once) where K > 0.
// After modifications, the task is to minimize the difference between the heights of the longest and the shortest tower and output its difference.

public class MaxMinDifferenceHeights {
    public static int getMinDiff (int[] arr, int size, int k) {
        Arrays.sort(arr);
        int tempMin = arr[0], tempMax = arr[size - 1];
        int result = tempMax - tempMin;
        for (int i = 0; i < size; i++) {
            if (arr[i] - k < 0) {
                continue;
            }

            tempMin = Math.min(arr[0] + k, arr[i] - k);
            tempMax = Math.max(arr[i - 1] + k, arr[size - 1] - k);
            result = Math.min(result, tempMax - tempMin);
            System.out.println(result + " result");
        }

        return result;
    }

    // Greedy Method
    public static int minimizeTowerHeightDifference (int arr[], int size, int k) {
        int result = arr[0];
        int minHeight = Arrays.stream(arr).min().getAsInt();
        int maxHeight = Arrays.stream(arr).max().getAsInt();
        int initialDiff = maxHeight - minHeight;
        int avgHeight = (maxHeight + minHeight) / 2;

        for (int i = 0; i < size; i++) {
            if (arr[i] <= avgHeight) {
                arr[i] += k;
            } else {
                arr[i] -= k;
            }
        }

        minHeight = Arrays.stream(arr).min().getAsInt();
        maxHeight = Arrays.stream(arr).max().getAsInt();

        int newDiff = maxHeight - minHeight;
        result = (newDiff <= initialDiff) ? newDiff : initialDiff;
        return result;
    }

    public static void main (String args[]) {
        int arr[] = { 7, 4, 8, 8, 8, 9};
        int k = 6, size = arr.length;
        // int ans = getMinDiff(arr, size, k);
        int ans = minimizeTowerHeightDifference(arr, size, k);
        System.out.println(ans);
    }
}
