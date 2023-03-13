package array;

import java.util.*;
import java.lang.Integer;

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

    // Using set DataStructure - Terrible Sol
    static Pair getKthMinMaxSet(int arr[], int size, int k) {
        Pair minMax = new Pair();
        // for storing elements in a sorted form we are using TreeSet
        Set<Integer> s = new TreeSet<Integer>();

        // Since counting start from
        int m = size - k;
        int n = --k;

        for (int i = 0; i < size; i++) {
            s.add(arr[i]);
        }

        Iterator maxItr = s.iterator();
        Iterator minItr = s.iterator();

        while (m > 0) {
            maxItr.next();
            m--;
        }

        while (n > 0) {
            minItr.next();
            n--;
        }

        minMax.min = (int) minItr.next();
        minMax.max = (int) maxItr.next();
        return minMax;
    }


    static void printArray (int arr[]) {
        for (int item : arr) {
            System.out.print(item);
            System.out.print(' ');
        }
        System.out.println();
    }

    public static void main (String args[]) {
        int arr[] = {7, 10, 4, 3, 20, 15};
        int k = 3, size = arr.length;
        printArray(arr);
//        Pair minMax = getKthMaxMin(arr, arr.length, k);
//        Pair minMax = getKthMinMaxSort(arr, size , k);
        Pair minMax = getKthMinMaxSet(arr, size, k);
        printArray(arr);
        System.out.println("Min value = " + minMax.min + " and Max value = " + minMax.max + " with k = " + k);
    }
}
