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

    static class MinHeap {
        int heapArray[];
        int capacity;
        int heapSize;

        MinHeap(int arr[], int size) {
            heapSize = size;
            heapArray = arr;
            int i = (heapSize - 1) / 2;
            while (i >= 0) {
                minHeapify(i);
                i--;
            }
        }

        int parent(int i) { return (i - 1) / 2; }
        int leftChild(int i) { return 2 * i + 1; }
        int rightChild(int i) { return 2 * i + 2; }

        void minHeapify(int i) {
            int leftIndex  = leftChild(i);
            int rightIndex = rightChild(i);
            int smallest = i;
            if (leftIndex < heapSize && heapArray[leftIndex] < heapArray[i]) {
                smallest = leftIndex;
            }
            if (rightIndex < heapSize && heapArray[rightIndex] < heapArray[smallest]) {
                smallest = rightIndex;
            }
            if (smallest != i) {
                int temp = heapArray[i];
                heapArray[i] = heapArray[smallest];
                heapArray[smallest] = temp;
                minHeapify(smallest);
            }
        }

        int getMin() { return heapArray[0]; }
        void replaceMax(int x) {
            this.heapArray[0] = x;
            minHeapify(0);
        }

        int extractMin() {
            if (heapSize == 0) {
                return Integer.MAX_VALUE;
            }

            // heap size is more than 1
            int root = heapArray[0];

            // if the heapArray has more than 1 items, move the last element to the root and minHeapify
            if (heapSize > 1) {
                heapArray[0] = heapArray[heapSize - 1];
                minHeapify(0);
                heapSize--;
            }
            return root;
        }
    }

    static void printArray (int arr[]) {
        for (int item : arr) {
            System.out.print(item);
            System.out.print(' ');
        }
        System.out.println();
    }

    static int kthSmallestMinHeap(int arr[], int size, int k) {
        MinHeap minHeap = new MinHeap(arr, size);

        for (int i = 0; i < k - 1; i++) {
            minHeap.extractMin();
        }
        return minHeap.getMin();
    }

    public static void main (String args[]) {
        int arr[] = {7, 10, 4, 3, 20, 15};
        int k = 3, size = arr.length;
        printArray(arr);
        //  Pair minMax = getKthMaxMin(arr, arr.length, k);
        //  Pair minMax = getKthMinMaxSort(arr, size , k);
        //  Pair minMax = getKthMinMaxSet(arr, size, k);
        int min = kthSmallestMinHeap(arr, size, k);
        printArray(arr);
        System.out.println(min);
        // System.out.println("Min value = " + minMax.min + " and Max value = " + minMax.max + " with k = " + k);
    }
}
