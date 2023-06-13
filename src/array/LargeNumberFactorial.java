package array;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.LinkedList;

public class LargeNumberFactorial {

    static void printArray(int[] arr) {
        int initial = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (initial == 0 && arr[i] != 0) {
                initial = 1;
            }
            if (initial == 1) {
                System.out.print(arr[i] + " ");
            }
        }
        System.out.println();
    }

    static void usingBigInteger(int val) {
        BigInteger f = new BigInteger("1");

        for (int i = 2; i <= val; i++) {
            f = f.multiply(BigInteger.valueOf(i));
        }
        System.out.print(f + " ");
    }

    static void usingLogarthimicEquation(int val) {
        // O(N)
        // ln(ab) = ln(a) + ln(b) e^(ln(N!)) = N!

        double sum = 0;
        for (int i = 1; i <= val; i++) {
            sum = sum + Math.log(i);
        }

        BigDecimal result = new BigDecimal(Math.exp(sum));
        result = result.setScale(0, RoundingMode.HALF_UP);

        System.out.print(result.toString());

    }

    static void largeNumberFactorial (int[] arr, int val) {
        int carry = 0;
        arr[0] = 1;
        for (int i = 0; i < val; i++) {
            for (int j = 0; j < arr.length; j++) {
                int prod = arr[j] * (i + 1) + carry;
                arr[j] = prod % 10;
                carry = prod / 10;
            }
        }
        printArray(arr);
    }

    static Integer[] multiply(int n, Integer[] digits) {
        int carry = 0, result = 0;
        for(int i = 0; i < digits.length; i++) {
            result = digits[i] * n + carry;
            digits[i] = result % 10;
            carry = result / 10;
        }

        LinkedList<Integer> v = new LinkedList<Integer>();
        v.addAll(Arrays.asList(digits));
        while (carry > 0) {
            v.add(carry % 10);
            carry = carry / 10;
        }

        return v.stream().toArray(Integer[]::new);
    }

    static Integer[] factorialUsingRecursion(int val) {
        // n*log(n)
        if (val <= 2) {
            return multiply(val, new Integer[]{1});
        }

        return multiply(val, factorialUsingRecursion(val - 1));
    }
    public static void main(String[] args) {
        int[] arr = new int[200];
        long startTime = System.currentTimeMillis();
//        largeNumberFactorial(arr, 100);
//        usingBigInteger(100);
//        usingLogarthimicEquation(100);
        Integer[] res = factorialUsingRecursion(100);
        for(int i = res.length - 1; i >=0; i--) {
            System.out.print(res[i]);
        }
        System.out.println();
        System.out.println(System.currentTimeMillis() - startTime + "ms time taken");


    }
}
