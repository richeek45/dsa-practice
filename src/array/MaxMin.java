package array;

public class MaxMin {

    static int maxValueInArray(int arr[], int size) {
        int maxValue = 0;
        for (int i = 0; i < size; i++) {
            if (arr[i] > maxValue) {
                maxValue = arr[i];
            }
        }
        return maxValue;
    }

    static int minValueInArray(int arr[], int size) {
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            if (arr[i] < minValue) {
                minValue = arr[i];
            }
        }
        return minValue;
    }


    static void printArray (int arr[], int size) {
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i]);
            System.out.print(' ');
        }
        System.out.println();
    }

    public static void main (String args[]) {
        int array[] = {13, 15, 3, 21, 17, 49, 29};
        int size = array.length;
        int maxValue = maxValueInArray(array, size);
        int minValue = minValueInArray(array, size);
        System.out.println("Max Value: " + maxValue + ", Min Value: " + minValue);
    }
}
