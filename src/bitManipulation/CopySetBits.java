package bitManipulation;

public class CopySetBits {
    // Given two numbers x and y, and a range [l, r] where 1 <= l, r <= 32.
    // The task is consider set bits of y in range [l, r] and set these bits in x also.
    // Examples :
    // Input  : x = 10, y = 13, l = 2, r = 3
    // Output : x = 14
    // Binary representation of 10 is 1010 and that of y is 1101.
    // There is one set bit in y at 3'rd position (in given range).
    // After we copy this bit to x, x becomes 1110 which is binary representation of 14.
    // Input  : x = 8, y = 7, l = 1, r = 2
    // Output : x = 11
    public static void copySetBits(int x, int y,int l, int r) {

        for (int i = l; i <= r; i++) {
            // move the mask to the i position
            int mask = (1 << (i-1));
            // checking if the i bit is set
            if ((mask  & y) != 0) {
                // setting the bit in x in i position
                x = x | mask;
            }
        }

        System.out.println(x);
    }

    public static void copySetBits2(int x, int y, int l, int r) {
        if (l < 1 || r > 32) {
            return;
        }

        // 1101101 -> 2-4
        // finding the masked bit from l to r
        int maskedBits = (1 << (r - l + 1)) - 1; // contains all setbits from 2 - 4
        // shifting to the appropriate postions for doing AND operation with y
        int mask = maskedBits << (l-1);
        int bits = mask & y; // getting the bits from y from pos r to l
        int newVal = x | bits;
        System.out.println(newVal);
    }

    public static void main(String[] args) {
        int x = 10, y = 13, l = 2, r = 3;
        copySetBits(x, y, l, r);

    }
}
