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

    static String binary(int N) {
        StringBuilder str = new StringBuilder();

        while(N > 0) {
            if ((N & 1) == 1) {
                str.append("1");
            } else {
                str.append("0");
            }
            N >>= 1;
        }

        return str.reverse().toString();
    }

    static void countBitIntegerConversion3(int a, int b) {
        String astr = binary(a);
        String bstr = binary(b);
        // length of the binary bits
        int alen = astr.length(), blen = bstr.length();
        int diff = Math.abs(alen - blen);
        int count = 0;

        if (alen > blen) {
            for(int i = 0; i < diff; i++) {
                if (astr.charAt(i) == '1') {
                    count++;
                }
            }
        } else if (blen > alen) {
            for(int i = 0; i < diff; i++) {
                if(bstr.charAt(i) == '1') {
                    count++;
                }
            }
        }
        // for starting the loop from the last index
        alen = alen - 1;
        blen = blen - 1;

        while(alen >= 0 && blen >= 0) {
            if (astr.charAt(alen) != bstr.charAt(blen)) {
                count++;
            }

            alen--;
            blen--;
        }

        System.out.println(count);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("First Number");
        int a = sc.nextInt();
        System.out.println("Second Number");
        int b = sc.nextInt();
//        countBitIntegerConversion(a, b);
//        countBitIntegerConversion2(a, b);
        countBitIntegerConversion3(a, b);
    }
}
