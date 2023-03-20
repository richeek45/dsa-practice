package array;

// finding the largest sum of contiguous array
public class LargestContiguousSum {
    static class MaxStartEnd {
        int maxSoFar;
        int start;
        int end;

        MaxStartEnd (int max, int s, int e) {
            maxSoFar = max;
            start = s;
            end = e;
        }
    }
    static MaxStartEnd contiguousArray(int arr[], int size) {
        MaxStartEnd maxValues = new MaxStartEnd(Integer.MIN_VALUE, 0, 0);
        int maxEndingHere = 0, s = 0;

        for (int i = 0; i < size; i++) {
            maxEndingHere += arr[i];
            if (maxValues.maxSoFar < maxEndingHere) {
                maxValues.maxSoFar = maxEndingHere;
                // updating the start and end index
                maxValues.start = s;
                maxValues.end = i;
            }
            if (maxEndingHere < 0) {
                maxEndingHere = 0;
                s = i + 1;  // restarting from the next index
            }
        }
        return maxValues;
    }

    public static void main (String args[]) {
        int[] arr = { -2, -3, 4, -1, -2, 1, 5, -3 };
        int size = arr.length;
        MaxStartEnd maximumValue = contiguousArray(arr, size);
        System.out.println("Max Value: " + maximumValue.maxSoFar + " start: " + maximumValue.start + " end: " + maximumValue.end);
    }
}
