package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// You are given an integer array nums and an integer goal.
// You want to choose a subsequence of nums such that the sum of its elements is the closest possible to goal.
// That is, if the sum of the subsequence's elements is sum, then you want to minimize the absolute difference abs(sum - goal).
// Return the minimum possible value of abs(sum - goal).

public class ClosestSubsequentSumTarget {
    static int[] first = new int[2000001];
    static int[] second = new int[2000001];

    static void calculatePossibleSum(int[] arr, int[] sums, int c, int n) {
        // 2^n combinations of the sum
        for (int i = 0; i < (1 << n); i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    sum += arr[j + c];
                }
            }
            sums[i] = sum;
        }
    }

    static int findLowerBound(int[] arr, int val, int size) {
        int left = -1, right = size;

        while (left + 1 < right) {
            int mid = (left + right) >>> 1;
            if (arr[mid] >= val) {
                right = mid;
            } else {
                left = mid;
            }
        }

        return right;
    }

    static int solveClosestSum(int[] nums, int target) {
        int n = nums.length;
        calculatePossibleSum(nums, first,  0, n/2);
        calculatePossibleSum(nums, second, n/2, (n - n/2));

        int size_x = 1 << n/2;
        int size_y = 1 << (n - n/2);

        int minDiff = Integer.MAX_VALUE;

        // sort the second array to do the binary search on the second array
        Arrays.sort(second, 0, size_y);

        for (int i = 0; i < size_x; i++) {
            // last value found or greater value
            int remaining = target - first[i];

            int index = findLowerBound(second, remaining, size_y);

            // find greater or less value Math.abs(target - arr[lowerBoundIndex]);
            if (index < size_y && second[index] == remaining) {
                return 0;
            }

            if (index < size_y) {
                minDiff = Math.min(minDiff, Math.abs(remaining - second[index]));
            }

            if (index > 0) {
                minDiff = Math.min(minDiff, Math.abs(remaining - second[index]));
            }


        }

        return minDiff;
    }

    static void generateSums(List<Integer> nums, int start, int end, List<Integer> sums) {
        for (int i = 0; i < (1 << end); i++) {
            int sum = 0;
            for (int j = 0; j < end; j++) {
                if ((i & (1 << j)) != 0) {
                    sum += nums.get(j + start);
                }
            }
            sums.add(sum);
        }
    }

    static int solveClosestSum2(List<Integer> nums, int target) {
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        int n = nums.size();

        generateSums(nums, 0, n/2, left);
        generateSums(nums, n/2, (n - n/2), right);

        Collections.sort(right);

        int minDiff = Integer.MAX_VALUE;

        for (int i = 0; i < left.size(); i++) {
            int remaining = target - left.get(i);

            int pos = Collections.binarySearch(right, remaining);

            if (pos >= 0) {
                return 0;
            }
            else {
                pos = -pos - 1; // Binary search returned
                // insertion point
            }

            if (pos < right.size()) {
                minDiff = Math.min(minDiff, Math.abs(remaining - right.get(pos)));
            }
            if (pos > 0) {
                minDiff = Math.min(minDiff, Math.abs(remaining - right.get(pos-1)));
            }
        }

        return minDiff;
    }

    public static void main(String[] args) {
        int[] arr = {5, -7, 3, 5};
        int target = 6;
        int[] arr1 = { 7,-9,15,-2 };
        int target1 = -5;
        int[] arr2 = { 1, 2, 3 };
        int target2 = -7;
        int[] arr3 = {-6651,401,-8998,-9269,-9167,7741,-9699};
        int target3 = 30536; // 22394 Wrong output: 2147483647

        List<Integer> arr4 = Arrays.asList(-6651,401,-8998,-9269,-9167,7741,-9699);


        int closestSum = solveClosestSum(arr2, target2);
        int closestSum2 = solveClosestSum2(Arrays.stream(arr3).boxed().toList(), target3);
        System.out.println(closestSum2);
    }
}
