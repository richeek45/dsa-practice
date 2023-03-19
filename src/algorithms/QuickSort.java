package algorithms;
// Implementation of Quick Sort - Divide and Conquer Algorithm
// Different ways to Quick Sort
// Pick the first element as the pivot
// Pick the last element as the pivot
public class QuickSort {

    static void swap (int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Quick Sort by Lomuto Method
    static int partitionByLomuto(int arr[], int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j <= high - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        // swap the pivot value with the i+1 index after sorting all the values before the pivot
        swap(arr, i + 1, high);
        return (i + 1);
    }

    static void quickSortByLomutoMethod(int arr[], int low, int high) {

        if (low < high) {
            int partitionIndex = partitionByLomuto(arr, low, high);
            quickSortByLomutoMethod(arr, low, partitionIndex - 1);
            quickSortByLomutoMethod(arr, partitionIndex + 1, high);
        }
    }

    // Quick Sort by Hoare Method
    static int partitionByHoare(int arr[], int low, int high) {
        int i = low - 1, j = high + 1;
        int pivot = arr[low];

        while (true) {
            do {
                i++;
            } while (arr[i] < pivot);

            do {
                j--;
            } while (arr[j] > pivot);

            if (i >= j) {
                return j;
            }

            swap(arr, i, j);
        }
    }

    static void quickSortByHoareMethod (int arr[], int low, int high) {
        if (low < high) {
            int partitionIndex = partitionByHoare(arr, low, high);
            quickSortByHoareMethod(arr, low, partitionIndex);
            quickSortByHoareMethod(arr, partitionIndex + 1, high);
        }
    }

    static void printArray (int arr[], int size) {
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main (String args[]) {
        int arr[] = {10, 80, 30, 90, 40, 50, 70};
        int size = arr.length;
        // quickSortByLomutoMethod(arr, 0, size - 1);
        quickSortByHoareMethod(arr, 0, size - 1);
        printArray(arr, size);
    }
}
