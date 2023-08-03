package searchingAndSorting;

import java.math.BigInteger;

public class MissingRepeatingNumber {
    static void findMissingRepeatingValue(int[] arr) {
        int i = 0, N = arr.length, count = 1;
        while (count < N) {
            int temp = arr[i];
            arr[i] = arr[temp-1];
            arr[temp-1] = temp;
            count++;
        }
        
        for (int j = 0; j < N; j++) {
            if (arr[j] != j+1) {
                System.out.print("Missing:" + (j+1) + ", Repeating: " + arr[j]);
            }
        }
    }

    static void findMissingRepeatingValue2(int[] arr) {
        int N = arr.length;
        int[] temp = new int[N];
        int repeating = -1;
        int missing = -1;

        for (int i = 0; i < N; i++) {
            temp[arr[i]-1]++;
            if (temp[arr[i]-1] > 1) {
                repeating = arr[i];
            }
        }

        for (int i = 0; i < N; i++) {
            if (temp[i] == 0) {
                missing = i+1;
            }
        }
        System.out.println(repeating + ":" +  missing);

    }

    static void findMissingRepeatingValue3(int[] arr) {
        // Using maths
        BigInteger n = BigInteger.valueOf(arr.length);
        BigInteger sum = BigInteger.valueOf(0);
        BigInteger squareSum = BigInteger.valueOf(0);

        // sum and square of an element
        for (int val: arr) {
            sum = sum.add(BigInteger.valueOf(val));
            squareSum = squareSum.add(BigInteger.valueOf(val).multiply(BigInteger.valueOf(val)));
        }

        BigInteger one = BigInteger.valueOf(1);
        BigInteger two = BigInteger.valueOf(2);
        BigInteger three = BigInteger.valueOf(3);
        // sum = n*(n+1)/2
        BigInteger sumN = n.multiply(n.add(one)).divide(two);
        // squareSum = n*(n+1)*(2*n+1)/2
        BigInteger squareN = sumN.multiply(two.multiply(n).add(one)).divide(three);
        // subtracting sum of all the numbers in array from sum of total numbers will give
        // x - y; where x, y are duplicate and missing numbers
        BigInteger sub = sumN.subtract(sum);  // x-y
        // finding x + y by subtracting square of numbers giving (x^2 - y^2) then dividing (x-y) to get (x+y)
        BigInteger add = (squareN.subtract(squareSum)).divide(sub); // x+y
        int missing = sub.add(add).divide(two).intValue();
        int duplicate = add.subtract(BigInteger.valueOf(missing)).intValue();
        System.out.println(missing + ":" + duplicate);


    }

    static void findMissingRepeatingValue4(int[] arr) {
        // using xor
        int N = arr.length;
        int x = 0, y = 0; // using for xor-ing x-for set bits and y- for non-set bits
        int setBitNo = getSetBitNo(arr, N); // has xor of x^y
        // getting the xor of set bit in x and not set bit in y
        for (int i = 0; i < N; i++) {
            if ((arr[i] & setBitNo) != 0) {
                x = x ^ arr[i];
            } else {
                y = y ^ arr[i];
            }
        }

        for (int i = 1; i <= N; i++) {
            if ((i & setBitNo) != 0) {
                x = x ^ i;
            } else {
                y = y ^ i;
            }
        }

        System.out.println(setBitNo + ":" + x + ":" + y);

    }

    private static int getSetBitNo(int[] arr, int N) {
        int xor1 = arr[0];
        int setBitNo = 0;

        // doing xor of all the numbers
        for (int i = 1; i < N; i++) {
            xor1 = xor1 ^ arr[i];
        }
        // doing xor of all numbers from 1...N with xor of arr elements
        // xor of a number itself is 0, n^n=0;
        // All the same number in both the cases should cancel out each other ending with xor of x^y
        for (int i = 1; i <= N; i++) {
            xor1 = xor1 ^ i;
        }
        // finding the rightmost setbit of xor1
        // (xor1-1) will have all the rightmost bits in opposite order and ~ will reverse the bits
        // doing a & operation will give the rightmost set bit
        setBitNo = xor1 & ~(xor1 - 1);
        return setBitNo;
    }

    public static void main(String[] args) {
        int[] arr = {4, 4, 5, 2, 6, 1};
        
//        findMissingRepeatingValue(arr);
//        findMissingRepeatingValue2(arr);
//        findMissingRepeatingValue3(arr);
        findMissingRepeatingValue4(arr);
//        System.out.println(xor + ":" + xor1 + " " + (xor ^ xor1));
    }
}
