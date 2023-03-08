package array;

public class ReverseArray {
    static void reverseArray(int arr[], int startIndex, int endIndex) {
        int temp = 0;
        while (startIndex < endIndex) {
            temp = arr[startIndex];
            arr[startIndex] = arr[endIndex];
            arr[endIndex] = temp;
            startIndex++;
            endIndex--;
        }
    }

    static void printArray(int arr[], int size ) {
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i]);
            System.out.print(' ');
        }
        System.out.println();
    }

    public static void main(String args[]) {
        int array[] = {13, 17, 5, 29, 7, 23};
        System.out.print("Original Array: ");
        printArray(array, array.length);
        reverseArray(array, 0, 5);
        System.out.print("Reversed Array: ");
        printArray(array, array.length);
    }
}
