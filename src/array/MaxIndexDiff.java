package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class MaxIndexDiff {

    static void findNextLargeValueIndexDiff(int[] arr) {
        // 34, 8, 10, 3, 2, 80, 30, 33, 1
        // Our goal here is to find the largest diff (j - i) between two indexes (i, j)
        // where larger index has a value larger than the smaller index value,
        //  i.e. arr[j] > arr[i] and j > i
        // ideal scenario is when both the index are at two ends of the array
        // I have an array 34, 8, 10, 3, 2, 80, 30, 33, 1, I am at 34 and I want 80
        // but I have to search through the numbers from the end to get to 80
        // we want to eliminate the steps traversing from the end because that is expensive or I am lazy
        // for 34 if we traverse from the end as soon as we find 80 we find the index diff and move to next iteration
        // for 8 we traverse again from the end to reach 33
        // for 10 we traverse again to get to 33 and so on for each traversal -> O(n^2)
        // is there any way to skip the traversal to find larger number greater than current number?

        // Monotoning decreasing array -> // 80, 80, 80, 80, 80, 80, 33, 33, 1
        // This tells us that every value before index i is smaller than maxFromEnd[i] value
        // perform a binary search on the new maxFromEnd array
        // how does this help
        // if I find a number larger than 34 during the search maxFromEnd[mid]
        // then we can conclude that our result is on the right side because maxFromEnd[mid] = max from end
        // so the actual max value could be on the left side or that exact value is the max value
        // else if I find a number smaller than 34 then we know the result is on the left side of the array
        // because that is the max value we have found so far from the end
        // Hence we use binary search to find the answer.

        // Here we create the array to store the max value from the end
        // the reason we want that is so that we don't need to search
        int len = arr.length;
        int[] maxFromEnd = new int[len + 1];
        for (int i = len - 1; i >= 0; i--) {
            maxFromEnd[i] = Math.max(maxFromEnd[i + 1], arr[i]);
        }

        int result = 0;
        for (int i = 0; i < len; i++) {
            int low = i + 1, high = len - 1, ans = i;

            while (low <= high) {
                int mid = (low + high) / 2;

                if (maxFromEnd[mid] >= arr[i]) {
                    // max could be on the right side
                    // we still calculate because this could be the answer
                    ans = Math.max(ans, mid);
                    low = mid + 1;
                } else {
                    // definitely on the left cause max from end so far is smaller than current value
                    // no point searching beyond the value
                    high = mid - 1;
                }
            }
            result = Math.max(result, ans - i);
        }

        System.out.println(result);
    }

    static void findNextLargeValueIndexDiff2(int[] arr) {
        // 34, 8, 10, 3, 2, 80, 30, 33, 1
        // Our goal here is to find the largest diff (j - i) between two indexes (i, j)
        // where larger index has a value larger than the smaller index value,
        //  i.e. arr[j] > arr[i] and j > i
        // here we store the index of the value in a hashmap stored in an array for handling duplicates
        // then sort the array -> {1: 8}, {2: 4}, {3: 3}, {8: 1}, {10: 2} , {30: 6}, {33: 7}, {34: 0}, {80: 5}
        // We have the index of each value of original array stored in the hashMap
        // We start to traverse the sorted array -> 1, 2, 3, 8, 10, 30, 33, 34, 80
        // we have a temp index = len-1 -> largest index because we want to find the smallest number
        // in the lower index
        // We find the index of each value in the hashMap
        // Two things could happen:
        // 1. We find the index of larger value to be smaller than previous smaller value in the original array (j < i)
        // -> think of index(8) of value 2 and index(4) of value 1.
        // we cannot find the diff as it violates the constraint
        // so we update the temp index to current index (j) till the value 8.
        // 2. We find the index of larger value to be larger than index of smaller value (j > i)
        // -> think of value 8(1) and 10(2).
        // we can start to find the diff and keep updating to find the max value in a result value

        HashMap<Integer, ArrayList<Integer>> hashMap = new HashMap<Integer, ArrayList<Integer>>();

        for (int i = 0; i < arr.length; i++) {
            if (hashMap.containsKey(arr[i])) {
                hashMap.get(arr[i]).add(i);
            } else {
                hashMap.put(arr[i], new ArrayList<>());
                hashMap.get(arr[i]).add(i);
            }
        }

        Arrays.sort(arr);

        int prevIndex = arr.length;
        int result = Integer.MIN_VALUE;
        for (int j : arr) {
            ArrayList<Integer> mapValue = hashMap.get(j);
            int currIndex = mapValue.get(0);

            if (currIndex < prevIndex) {
                prevIndex = currIndex;
            }
            int index = mapValue.get(mapValue.size() - 1);
            result = Math.max(result, index - prevIndex);
        }

        System.out.println(result);
    }

    static void findNextLargeValueIndexDiff3(int[] arr) {
        int n = arr.length, i = 0, j = 0;
        int[] lMin = new int[n + 1];
        int[] rMax = new int[n + 1];

        lMin[0] = arr[0];
        for (i = 1; i < n; i++) {
            lMin[i] = Math.min(lMin[i - 1], arr[i]);
        }

        rMax[n - 1] = arr[n - 1];
        for (j = n - 2; j >= 0; j--) {
            rMax[j] = Math.max(rMax[j + 1], arr[j]);
        }

        i = 0;
        j = 0;
        int maxDiff = -1;
        while (i < n && j < n) {
            if ((rMax[j] - lMin[i]) >= 0) {
                // max value is on the right side of the array
                maxDiff = Math.max(maxDiff, (j - i));
                j = j + 1;
            } else {
                i = i + 1;
            }
        }
        // O(n)
        System.out.println(maxDiff);
    }

    public static void main(String[] args) {
        int[] arr = {34, 8, 10, 3, 2, 80, 30, 33, 1}; // 6  (j = 7, i = 1)
        int[] arr2 = { 9, 2, 3, 4, 5, 6, 7, 8, 18, 0 };
//        findNextLargeValueIndexDiff(arr);
//        findNextLargeValueIndexDiff2(arr);
        findNextLargeValueIndexDiff3(arr);
    }

}
