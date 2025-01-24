package array;

public class MaxCircularSubArray {

    static void maximumCircularSubArraySum(int[] arr) {
        int n = arr.length;
        int[] suffixMax = new int[n];

        suffixMax[n-1] = arr[n-1];
        for (int i = n-2; i >= 0; i--) {
            suffixMax[i] = Math.max(suffixMax[i+1], suffixMax[i] + arr[i]);
        }

        int normalSum = 0;
        int circularSum = arr[0];
        int currSum = 0;
        int prefix = 0;
        for (int i = 0; i < n-1; i++) {
            // max sub array sum -> max(sum, arr[i])
            currSum = Math.max(currSum + arr[i], arr[i]);
            normalSum = Math.max(normalSum, currSum);

            // circular sum -> max sum from both ends
            prefix = prefix + arr[i];
            circularSum = Math.max(circularSum, suffixMax[i+1] + prefix);
        }

        int result = Math.max(normalSum, circularSum);
        System.out.println(result);
    }

    static void maximumCircularSubArraySum1(int[] arr) {
        // finding the minimum sub array using Kadane Algorithm
        int totalSum = 0;
        int n = arr.length;
        int currMaxSum = 0, currMinSum = 0;
        int maxSum = arr[0], minSum = arr[0];
        for (int i = 0; i < n; i++) {
            currMaxSum = Math.max(currMaxSum+arr[i], arr[i]);
            maxSum = Math.max(maxSum, currMaxSum);

            currMinSum = Math.min(currMinSum+arr[i], arr[i]);
            minSum = Math.min(minSum, currMinSum);
            totalSum += arr[i];
        }
        int normalSum = maxSum;
        int circularSum = totalSum - minSum;
        int result = Math.max(normalSum, circularSum);
        System.out.println(result);
    }

    public static void main(String[] args) {
        int[] arr = {8, -8, 9, -9, 10, -11, 12};

        maximumCircularSubArraySum1(arr);
    }
}
