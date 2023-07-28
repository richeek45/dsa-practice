package searchingAndSorting;

public class FirstAndLastPos {
    // Given a sorted array arr[] with possibly duplicate elements,
    // the task is to find indexes of the first and last occurrences of an element x in the given array.
    static void findFirstLastPosition(int[] arr, int val) {
        int first = -1, count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == val && first == -1) {
                first = i;
                count++;
            } else if (arr[i] == val) {
                count++;
            }
        }
        System.out.println(first + ", " + (first + count - 1));
    }

    static int findFirstLastPosition2(int[] arr, int val, boolean findFirst) {
        // using binary search
        int ans = -1;
        int N = arr.length;
        int high = N-1, low = 0;
        while(low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] < val) {
                low = mid + 1;
            } else if (arr[mid] > val) {
                high = mid - 1;
            } else {
                // element is found
                ans = mid;
                if (findFirst) {
                    // if searching for the first occurrence of the element, closing the end
                    high = mid - 1;
                } else {
                    // if
                    low = mid + 1;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr =  {1, 3, 5, 5, 5, 5, 67, 123, 125};
        int[] arr2 = {1, 3, 4, 4, 5, 5, 5, 5, 5, 7, 123, 125 };
        int first = findFirstLastPosition2(arr2, 5, true);
        int last = findFirstLastPosition2(arr2, 5, false);
        System.out.println(first + ", " + last);
    }
}
