package array;

import java.util.*;

// Find the kth maximum and minimum element in an unsorted array
public class KthElementInArray {
    static class Pair  {
        int max;
        int min;
    }
    static Pair getKthMaxMin(int arr[], int size, int k) {
        Pair minMax = new Pair();
        for (int i = 1; i < size; i++) {
            int temp = 0, j = i;
            while (j > 0) {
                if (arr[j] < arr[j - 1] ) {
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
                j--;
            }
        }
        minMax.min = arr[k-1];
        minMax.max = arr[size - k];

        return minMax;
    }

    // Using Sorting
    static Pair getKthMinMaxSort(int arr[], int size, int k) {
        Pair minMax = new Pair();
        Arrays.sort(arr);
        minMax.min = arr[k-1];
        minMax.max = arr[size - k + 1];
        return minMax;
    }

    // Using set DataStructure



    static void printArray (int arr[]) {
        for (int item : arr) {
            System.out.print(item);
            System.out.print(' ');
        }
        System.out.println();
    }

    public static void main (String args[]) {
        int arr[] = {7, 10, 4, 3, 20, 15};
        int k = 3;
        printArray(arr);
//        Pair minMax = getKthMaxMin(arr, arr.length, k);
        Pair minMax = getKthMinMaxSort(arr, arr.length, k);
        printArray(arr);
        System.out.println("Min value = " + minMax.min + " and Max value = " + minMax.max + " with k = " + k);
    }
}
