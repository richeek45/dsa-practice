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

    static void printArray (int arr[], int size) {
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main (String args[]) {
        int arr[] = { 0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1 };
        int size = arr.length;
        sortArrayByCounting(arr, size);
        printArray(arr, size);
    }
}
