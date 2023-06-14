package array;

import java.util.HashSet;
import java.util.Set;
import java.lang.Integer;

public class SubArraySumZero {
    static void zeroSubArray1(int[] arr) {
        // O(N^2)
        int sum;
        boolean zeroSum = false;
        for (int i = 0; i < arr.length; i++) {
            sum = 0;
            for (int j = i + 1; j < arr.length; j++) {
                sum += arr[j];
                if (sum == 0) {
                    zeroSum = true;
                }
            }
        }
        System.out.println(zeroSum + " zero sum");
    }

    static void zeroSubArrayHashSet(int[] arr) {
        // add the sum of each index from 0 -> i with sum+=arr[i] if a value exists twice then zero sum = true
        Set<Integer> hs = new HashSet<Integer>();
        int sum = 0;
        boolean zeroSum = false;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (arr[i] == 0 || sum == 0 || hs.contains(sum)) {
                zeroSum = true;
            }
            hs.add(sum);
        }
        System.out.println("Zero sum: " + zeroSum);
    }

    public static void main(String[] args) {
        int[] arr1 = { -3, 2, 3, 1, 6 };
        int[] arr2 = {4, 2, -3, 1, 6};
        int[] arr3 = {4, 2, 0, 1, 6};

//        zeroSubArray1(arr3);
        zeroSubArrayHashSet(arr3);

    }
}
