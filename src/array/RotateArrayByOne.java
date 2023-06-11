package array;

// Cyclically rotate an array by one
public class RotateArrayByOne {

    static void rotateOne (int arr[], int size) {
        int lastValue = arr[size - 1];
        for (int i = size - 1; i > 0; i--) {
            arr[i] = arr[i - 1];
        }
        arr[0] = lastValue;
    }

    static void rotateOneByReverse (int arr[], int size) {
        int i = 0, j = size - 1;

        while (i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
        }
    }

    static void swap (int arr[], int i,int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static void rotateArrayKtimes (int arr[], int size, int k) {

        // rotate the first n - 1 terms for k = 1 and n - k - 1 for k times
        // swap from i =0 with j = size - k - 1,
        // then swap again from i =0 to j = n - 1 -> reversing array
        // then swap i = 0 to j = k -1
        // clockwise rotate
        for (int i = 0, j = size - k - 1; i < j; i++, j--) {
            swap(arr, i, j);
        }
        printArray(arr, size);

        // reverse the entire array
        for (int i = 0, j = size - 1; i < j; i++, j--) {
            swap(arr, i, j);
        }

        for (int i = 0, j = k - 1; i < j; i++, j--) {
            swap(arr, i, j);
        }

    }

    static void printArray(int arr[], int size) {
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main (String args[]) {
        int arr[] = {1, 2, 3, 4, 5, 6, 7, 8};
        int size = arr.length;
        int k = 4;
        // rotateOne(arr, size);
        // rotateOneByReverse(arr, size);
        rotateArrayKtimes(arr, size, k);
        printArray(arr, size);
    }
}
