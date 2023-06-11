package array;
// https://www.geeksforgeeks.org/merge-two-sorted-arrays-o1-extra-space/

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MergeTwoSortedArray {
    static void  printArray(int[] arr) {
        System.out.print("array : ");
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
    static ArrayList<Integer> mergeSortedArray(int[] arr1, int[] arr2) {
        ArrayList<Integer> res = new ArrayList<>();
        int n1 = arr1.length;
        int n2 = arr2.length;
        int i = 0, j = 0;
        while (i < n1 && j < n2) {
            if (arr1[i] < arr2[j]) {
                res.add(arr1[i]);
                i++;
            } else if (arr2[j] < arr1[i]) {
                res.add(arr2[j]);
                j++;
            } else if (arr1[i] == arr2[j]) {
                res.add(arr1[i]);
                res.add(arr2[j]);
                i++;
                j++;
            }
        }

        while (i < n1) {
            res.add(arr1[i]);
            i++;
        }
        while (j < n2) {
            res.add(arr2[j]);
            j++;
        }

        return res;
    }

    static void mergeSortedArrayInPlace(int[] arr1, int[] arr2) {
        //  O(M * N)
        // i index for arr2, j index for arr1, i = n1-1, j = n2 - 1
        // take last element of arr2[n2-1] and compare it with second last element of arr1[j]
        // shift arr1[j] -> arr1[j+1] until arr1[j] > arr2[i] -> arr1[j + 1] = arr2[i] (arr2 element sorted in place)
        // Repeat with arr2[j] with j--
        int n1 = arr1.length, n2 = arr2.length;
        for (int i = n2 - 1; i >= 0; i--) {

            int j, last = arr1[n1 - 1];
            for (j = n1 - 2; j >= 0 && arr1[j] > arr2[i]; j--) {
                if (arr1[j] > arr2[i]) {
                    arr1[j + 1] = arr1[j];
                }
            }
            arr1[j + 1] = arr2[i];
            arr2[i] = last;
        }

        printArray(arr1);
        printArray(arr2);
    }

    static void mergeSortedArrayInPlace2(int[] arr1, int[] arr2) {
        // O (m + n) * log(m + n)
        // i, j = 0, k = n1 - 1 (last index)
        // 1. compare if arr1[i] > arr2[j] ->
        // true ? 2. swap(arr1[k], arr2[j]) ->  k--, j++ : false -> -> i++
        // -> step 1
        int n1 = arr1.length, n2 = arr2.length;
        int i = 0, j = 0, k = n1 - 1;

        while (i <= k && j < n2) {
            if (arr1[i] > arr2[j]) {
                int last = arr1[k];
                arr1[k] = arr2[j];
                arr2[j] = last;
                k--;
                j++;
                continue;
            }
            i++;
        }
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        printArray(arr1);
        printArray(arr2);
    }

    static void mergeSortedArrayInPlace3(int[] arr1, int[] arr2) {
        // O(N1 * (N2 * logN2))
        // run the loop until last element of arr1 > first element of arr2
        // check first element of arr2[0] with i element of arr1[i] starts from 0 - (n - 1)
        // if arr1[i] > arr2[0] -> swap -> i++ -> sort arr2 -> step 1 again.
        int n1 = arr1.length, n2 = arr2.length;
        int i = 0, j = 0;
        int temp = 0;
        while (arr1[n1 - 1] > arr2[0]) {
            if (arr1[i] > arr2[0]) {
                temp = arr1[i];
                arr1[i] = arr2[0];
                arr2[0] = temp;
                Arrays.sort(arr2);
            }
            i++;
        }
        printArray(arr1);
        printArray(arr2);
    }

    static void swap(int[] arr1, int[] arr2, int i, int j) {
        int temp = arr1[i];
        arr1[i] = arr2[j];
        arr2[j] = temp;
    }
    static void rotateAntiClockWise(int[] arr, int k) {
        // rotate counter-clockwise
        // swap i = 0 with i = k -1 k/2 times with decreasing index
        // swap i = k with i = n - 1 k/2 times with decreasing index
        // swap the entire array n/2 times with decreasing index
        int size = arr.length;

        for (int i = 0; i < k / 2; i++) {
            swap(arr, arr, i, (k - 1 - i));
        }
        for (int i = k; i < (size + k) / 2; i++) {
            swap(arr, arr, i, (size - 1 - (i - k)));
        }
        for (int i = 0; i < size / 2; i++) {
            swap(arr, arr, i, (size - 1 - i));
        }
    }

    static void mergeArraySol(int[] arr1, int[] arr2) {
        // end result -> arr1 will have the smaller elements
        // arr2 will have the larger elements
        // n1 = arr1.length, n2 = arr2.length n1 < n2

        int n1 = arr1.length, n2 = arr2.length;
        int start = 0, end = n1 - 1;
        int index = 0; // index from which swapping should be done
        while (start <= end) {
            // Take the median1 of the smaller array
            int median1 = (start + end) / 2;
            int median2 = n2 - median1 - 1;
            // compare the crossing element of both arrays
            // array1 is supposed to be smaller array2 is supposed to be greater
            int arr1Median = arr1[median1];
            int arr1Next = median1 == (n1 - 1) ? Integer.MAX_VALUE : arr1[median1 + 1];
            int arr2Median = arr2[median2 - 1];
            int arr2Next = median2 == n2 ? Integer.MAX_VALUE : arr2[median2];

            if (arr1Median > arr2Next) {
                // the actual index is in left hand side, so we limit the end before median1
                end = median1 - 1;
                if (end == -1) {
                    index = 0;  // if index = 0 the swapping should be done from the beginning
                }
            } else if (arr1Next < arr2Median) {
                // the right hand has all the smaller elements in arr1, so no swapping is required
                // the actual index is in right hand side, limit the start to the median1 + 1
                start = median1 + 1;
                if (start == n1 - 1) {
                    // larger values not found, all elements in arr1 is small, no swapping required
                    index = n1;
                }
            } else {
                // the index is found
                index = median1 + 1;
                break;
            }
        }

        // swapping the elements of arr1 with arr2 from the index
        // arr1 contains the larger elements, arr2 contains the smaller elements from the array
        for (int i = index; i < n1; i++) {
            swap(arr1, arr2, i, (i - index));
        }

        Arrays.sort(arr1);
        Arrays.sort(arr2);
    }
    static void mergeSortedArrayInPlace4(int[] arr1, int[] arr2) {
        int n1 = arr1.length, n2 = arr2.length;

        if (n1 > n2) {
             mergeArraySol(arr2, arr1); // smaller array 1st prop, larger array 2nd prop;
            // end result will have smaller elements in arr2 and larger elements in arr1
            // ex -> arr1[] = {4, 6, 7, 8, 9, 10} arr2[] = {1, 2, 3}
            // but arr1 should have smaller elements and arr2 larger elements
            // arr1 = {1, 2, 3, 4, 6, 7} arr2 = {8, 9, 10}
            // rotate counter-clockwise the length difference in arr1 and swap the first n2 elements with arr2
            rotateAntiClockWise(arr1, (n1 - n2));
            for (int i = 0; i < n2; i++) {
                swap(arr1, arr2, i, i);
            }
        } else {
            mergeArraySol(arr1, arr2);
        }

        printArray(arr1);
        printArray(arr2);

    }

    // Merge two sorted arrays with O(1) extra space using Euclidean Division Lemma:
    //We can merge the two arrays as in merge sort
    // and simultaneously use Euclidean Division Lemma i.e. (((Operation on array) % x) * x).
    static void mergeSortedArrayInPlace5(int[] arr1, int[] arr2) {

    }

    public static void main (String[] args) {
        int[] arr1 = {1, 3, 4, 5};
        int[] arr2 = {2, 4, 6, 8};
        int[] arr3 =  {1, 5, 9, 10, 15, 20};
        int[] arr4 = {2, 3, 8, 13};

//        ArrayList<Integer> ans = mergeSortedArray(arr1, arr2);
//        for (Integer i : ans) {
//            System.out.print(i + " ");
//        }
//        mergeSortedArrayInPlace(arr3, arr4);
//        mergeSortedArrayInPlace2(arr3, arr4);
//        mergeSortedArrayInPlace3(arr3, arr4);
        mergeSortedArrayInPlace4(arr3, arr4);
    }
}
