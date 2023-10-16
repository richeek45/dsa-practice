package bitManipulation;

public class RotateBits {
    static final long bits = 8;
    public static long binary(long n) {
        long number = 0;
        long power = 0;
        while(n > 0) {
            long x = (n & 1);
            number += (long)(Math.pow(10, power) * x);
            n = n >> 1;
            power++;
        }
        return number;
    }

    public static int decimal(int n) {
        int number = 0;
        int power = 0;
        // 1111
        while(n > 0) {
            int x = n % 10;
            number += (int)(Math.pow(2, power) * x);
            n = (n / 10);
            power++;
        }
        return number;
    }

    public static void leftRotateBits(int n, int shift) {
        // n = 11100101, d = 3
        int leftShift = n << shift;
        int rightShift = n >> (bits - shift);
        int sol = (leftShift | rightShift) & 0xFFFF;
        System.out.println(binary(sol));
    }

    public static void rightRotateBits(int n, int shift) {
        int rightShift = n >> shift;
        int leftShift = n << (bits - shift);
        int sol = (rightShift | leftShift) & 0xFFFF;
        System.out.println(binary(sol));
    }

    public static void main(String[] args) {
        int n = 229; // 11100101
        int shift = 3;
        int n1 = 16, d = 2;
//        System.out.println(binary(n));
        leftRotateBits(n, shift); // 00101111
        rightRotateBits(n, shift);
    }
}
