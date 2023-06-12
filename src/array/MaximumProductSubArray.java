package array;

public class MaximumProductSubArray {
    static void maximumSubArray(int[] arr) {
        int len = arr.length;
        int maxProduct = arr[0], minVal = arr[0], maxVal = arr[0], start = 0, end = -1;
        for (int i = 1; i < len; i++) {
            if (arr[i] < 0) {
                int temp = minVal;
                minVal = maxVal;
                maxVal = temp;
            }

            maxVal = Math.max(arr[i], maxVal * arr[i]);
            minVal = Math.min(arr[i], minVal * arr[i]);
            System.out.println(maxVal + " " + minVal);
            maxProduct = Math.max(maxProduct, maxVal);
        }
        System.out.println(maxProduct + " " + start + " " +end);
    }

    static void maximumSubArray1(int[] arr) {
        // O(n2)
        int result = arr[0], len = arr.length;
        for (int i = 0; i < len; i++) {
            int mul = arr[i];
            for (int j = i + 1; j < len; j++) {
                result = Math.max(result, mul);
                mul *= arr[j];
            }
            result = Math.max(result, mul);
        }
        System.out.println(result);
    }

    static void maximumSubArray2(int[] arr) {
        int maxSoFar = 0, maxEndingHere = 1, minEndingHere = 1, flag = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                maxEndingHere *= arr[i];
                minEndingHere = Math.min(minEndingHere * arr[i], 1);
                flag = 1;
            } else if (arr[i] == 0) {
                maxEndingHere = 1;
                minEndingHere = 1;
            } else { // -ve values -> Find the max between current -ve * prev -ve = +ve value and 1
                // multiply arr[i] * maxVal -> minVal, multiply arr[i] * minVal -> maxVal
                int temp = maxEndingHere;
                maxEndingHere = Math.max(arr[i] * minEndingHere, 1);
                minEndingHere = arr[i] * temp;
            }

            if (maxEndingHere > maxSoFar) {
                maxSoFar = maxEndingHere;
            }
            System.out.println(maxSoFar + " " + maxEndingHere + " " + minEndingHere);
        }

        if (flag == 0 && maxSoFar == 0) {
            System.out.println("No output");
            return;
        }
        System.out.println(maxSoFar);

    }

    static void maximumSubArray3(int[] arr) {
        int maxSoFar = 0, maxEndingHere = 1, minEndingHere = 1;
        for (int i =0; i < arr.length; i++) {
            int tempMinEnding = Math.min(Math.min(arr[i], arr[i] * minEndingHere), arr[i] * maxEndingHere);
            maxEndingHere = Math.max(Math.max(arr[i], arr[i] * maxEndingHere), arr[i] * minEndingHere);
            minEndingHere = tempMinEnding;
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        System.out.println(maxSoFar);
    }

    public static void main (String[] args) {
        int[] arr1 = { 6, -3, -10, 0, 2, 25, 5 };
        int[] arr2 =  {-1, -3, -10, 0, 60 };
        int[] arr3 = { -2, -3, 0, -2, -40 };
        int[] arr4 = { 1, -2, -3, 0, 7, -8, -2 };

        maximumSubArray3(arr4);


    }
}
