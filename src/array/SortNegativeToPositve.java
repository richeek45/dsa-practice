package array;

// Move all negative numbers to beginning and positive to end with constant extra space
public class SortNegativeToPositve {

    // Using Dutch National Flag Approach
    static void negativeToPositiveUsingDNF (int arr[], int size) {
        int low = 0, high = size - 1;
        while (low <= high) {
            if (arr[low] < 0) {
                low++;
            } else {
                swap(arr, low, high);
                high--;
            }
        }
    }

    static void swap (int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static void printArray(int arr[], int size) {
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void main (String args[]) {
        int arr[] = { 1, 2,  -4, -5, 2, -7, 3, 2, -6, -8, -9, 3, 2,  1 };
        int size = arr.length;
        negativeToPositiveUsingDNF(arr, size);
        printArray(arr, size);

    }
}
