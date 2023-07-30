package searchingAndSorting;

public class SearchInCircularArray {
    private static int findPivot(int[] arr, int start, int end) {
        // using binary search finding the point at which array is rotated -> last index / the greatest element
        if (start > end) {
            // no pivot is found
            return  -1;
        }
        if (end == start) {
            return start;
        }
        int mid = start + (end - start) / 2;
        if (mid < end && arr[mid+1] < arr[mid]) {
            return mid;
        }
        if (mid > start && arr[mid-1] > arr[mid]) {
            return mid+1;
        }
        if (arr[start] > arr[mid]) {
            return findPivot(arr, start, mid-1);
        }
        return findPivot(arr, mid+1, end);
    }

    private static int binarySearch(int[] arr, int start, int end, int key) {
        if (start > end) {
            return -1;
        }
        int mid = start + (end - start) / 2;
        if (key == arr[mid]) {
            return mid;
        } else if (arr[mid] > key) {
            return binarySearch(arr, start, mid - 1, key);
        } else if (arr[mid] < key) {
            return binarySearch(arr, mid + 1, end, key);
        }
        return -1;
    }

    static int findValue(int[] arr, int key) {
        int pivot = findPivot(arr, 0, arr.length-1);
        if (pivot == -1) {
             return binarySearch(arr, 0, arr.length-1, key);
        }

        if (arr[pivot] == key) {
            return pivot;
        } else if (key < arr[0]) {
            return binarySearch(arr, pivot+1, arr.length-1, key);
        } else if (key > arr[0]) {
            return binarySearch(arr, 0, pivot-1, key);
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {7, 8, 9, 10, 1, 2, 3, 5, 6};

        int found = findValue(arr, 3);
        System.out.println(found);
    }
}
