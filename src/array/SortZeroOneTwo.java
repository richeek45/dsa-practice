package array;
// Sort an array of 0, 1 and 2
public class SortZeroOneTwo {

    // Using counting method
    static void sortArrayByCounting (int arr[], int size) {
        int cnt0 = 0, cnt1 = 0, cnt2 = 0;
        // Count the number of 0, 1, 2
        for (int i = 0; i < size; i++) {
            switch(arr[i]) {
                case 0:
                    cnt0++;
                    break;
                case 1:
                    cnt1++;
                    break;
                case 2:
                    cnt2++;
                    break;
            }
        }

        int i = 0;
        while (cnt0 > 0) {
            arr[i++] = 0;
            cnt0--;
        }
        while (cnt1 > 0) {
            arr[i++] = 1;
            cnt1--;
        }
        while (cnt2 > 0) {
            arr[i++] = 2;
            cnt2--;
        }

    }

    // Using Pointer Approach
    static void sortArrayUsingPointer (int arr[], int size) {
        int low = 0, mid = 0, high = size - 1, temp = 0;
        // iterating through the mid
        while (mid <= high) {
            switch (arr[mid]) {
                case 0:
                    temp = arr[low];
                    arr[low] = arr[mid];
                    arr[mid] = temp;
                    low++;
                    mid++;
                    break;
                case 1:
                    mid++;
                    break;
                case 2:
                    temp = arr[high];
                    arr[high] = arr[mid];
                    arr[mid] = temp;
                    high--;
                    break;
            }
        }
    }

    static void printArray (int arr[], int size) {
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main (String args[]) {
        int arr[] = { 0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1 };
        int size = arr.length;
        // sortArrayByCounting(arr, size);
        sortArrayUsingPointer(arr, size);
        printArray(arr, size);
    }
}
