package searchingAndSorting;

public class FixedPointArray {
    // Given an array of n distinct integers sorted in ascending order,
    // write a function that returns a Fixed Point in the array, if there is any Fixed Point present in array, else returns -1.
    // Fixed Point in an array is an index i such that arr[i] is equal to i. Note that integers in array can be negative.
    static void findFixedPoint(int[] arr) {
        // using linear search
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            if (arr[i] == i) {
                System.out.println(i);
            }
        }
    }

    static void findFixedPoint2(int[] arr) {
        // Using Binary Search
        int start = 0, end = arr.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (arr[mid] == mid) {
                System.out.println(mid);
                break;
            } else if (arr[mid] > mid) {
                // if the value at mid -> arr[mid] > mid then search the left side of mid
                // values after mid index is always greater than mid
                end = mid - 1;
            } else if (arr[mid] < mid) {
                // meaning all the values on the left side is going to be less than index value
                // hence search the right side
                start = mid + 1;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = { -10, -1, 0, 3, 10, 11, 30, 50, 100 };
        findFixedPoint(arr);
        findFixedPoint2(arr);
    }
}
