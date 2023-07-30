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

    static int findElementUsingBinarySearch2(int[] arr, int start, int end, int key) {
        // Using binary search in one traversal

        int mid = start + (end - start) / 2;
        if (arr[mid] == key) {
            return mid;
        }

        // for duplicates start and end could be same even though there is rotation
        // so we ignore start and end by reducing the search area
        if (arr[start] == arr[mid] && arr[end] == arr[mid]) {
            start++;
            end--;
            return findElementUsingBinarySearch2(arr, start, end, key);
        }

        if (arr[start] <= arr[mid]) {
            // array from [start...mid] is sorted
            if (key >= arr[start] && key <= arr[mid]) {
                // search in the left sub-array
                return findElementUsingBinarySearch2(arr, start, mid-1, key);
            }
            // search in the right sub-array
            return findElementUsingBinarySearch2(arr, mid+1, end, key);
        }
        // [mid+1...end] is sorted
        if (key > arr[mid+1] && key < arr[end]) {
            return findElementUsingBinarySearch2(arr, mid+1, end, key);
        }
        // search in the left sub-array
        return findElementUsingBinarySearch2(arr, start, mid-1, key);
    }

    public static void main(String[] args) {
        int[] arr = {7, 8, 9, 10, 1, 2, 3, 5, 6};
        int[] arr2 = {2, 2, 2, 3, 3, 4, 4, 1, 2, 2, 2};

//        int found = findValue(arr, 3);
        int found = findElementUsingBinarySearch2(arr2, 0, arr2.length-1, 1);
        System.out.println(found);

    }
}
