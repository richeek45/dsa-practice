package bitManipulation;

import java.util.Scanner;

public class TotalSetBits {
    // 00 01 10 11 100 101 110 111 1000 1001 1010 1011 1100 1101 1110 1111
    // all the numbers from the rightside 1st bit has alternating in every 2^0 change in numbers, 0,1,0,1,0,1... bits
    // 2nd bit has bits alternating in every 2^1 numbers, 0,0,1,1,0,0,1,1,..
    // 3rd bits has alternating in every 2^2 numbers, 0,0,0,0,1,1,1,1,.., etc.
    static void findSetBits(int N) {
        int countBits = 0;
        for (int i = 1; i <= N; i++) {
            int x = i;
            while(x != 0) {
                if ((x & 1) == 1) {
                    countBits++;
                }
                x = x >> 1;
            }
        }
        System.out.println(countBits);
    }

    private static int countBitsUtil(int x) {
        // time -> log(x)
        if (x <= 0) {
            // after recursive call when x becomes 0
            return 0;
        }
        // if x is an even number the first bit is always 0. Then right-shifting the x (x/2)
        // and calling recursively to get the all bits of a number
        return ((x % 2 == 0) ? 0 : 1) + countBitsUtil(x / 2);
    }

    static void findSetBits2(int N) {
        // right-shift of a number is N/2 and left-shift is N*2
        int countBits = 0;
        for (int i = 1; i <= N; i++) {
            countBits += countBitsUtil(i);
        }
        System.out.println(countBits);
    }

    static void findSetBits3(int N) {
        int i = 0;
        int countBits = 0;
        // left-shifting 1 by i bits i.e. multiply by 2 while the number is less than N
        while((1 << i) <= N) {
            boolean k = false; // for flipping the bit value
            int change = 1 << i; // alternating bits for i-th bit, i=bit no. change=bits
            // (i = 0, change=1), (i=1, change=2), (i=2, change=4)
            // for i = 0 flip for every other number,
            // for i = 1, flip bits for every two consequetive numbers
            // for i = 1, flip bits for every 4 consequetive numbers
            // iterating through i bit of all the numbers from 1 to N
            for (int j = 0; j <= N; j++) {
                if (k) {
                    countBits += 1;
                }

                if (change == 1) {
                    k = !k; // flipping the bits
                    change = 1 << i; // resetting the change for alternating
                } else {
                    change--;
                }
            }
            i++;
        }
        System.out.println(countBits);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("enter the val: ");
        int N = sc.nextInt();
        findSetBits3(N);
    }
}
