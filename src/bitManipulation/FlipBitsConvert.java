package bitManipulation;

import java.util.Scanner;

public class FlipBitsConvert {
    // 00001010 = 10
    // 00010100 = 20
    // 00011110 = xor
    // Count the number of bits to be flipped to convert A to B
    static void countBitIntegerConversion(int a, int b) {
        int xor = a ^ b; // xor value will have the uneven bits
        int count = 0;
        while(xor != 0) {
            xor = xor >> 1;
            if ((xor & 1) == 1) {
                count++;
            }
        }
        System.out.println("Count of number of bits: " + count);
    }

    static void countBitIntegerConversion2(int a, int b) {
        int flip = 0;

        while(a != 0 || b != 0) {

            if((a & 1) != (b & 1)) {
                flip++;
            }
            a >>= 1;
            b >>= 1;
        }
        System.out.println(flip);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("First Number");
        int a = sc.nextInt();
        System.out.println("Second Number");
        int b = sc.nextInt();
//        countBitIntegerConversion(a, b);
        countBitIntegerConversion2(a, b);
    }
}
