package bitManipulation;

public class CountSetBits {
    static void findSetBits(int val) {
        int count = 0;
        while(val > 0) {
            if ((val & 1) == 1) {
                count++;
            }
            val >>= 1;
        }

        System.out.println(count);
    }

    static void findSetBits2(int val) {
        // Brian Kernighan’s Algorithm
        // Subtracting 1 from a decimal number flips all the bits
        // after the rightmost set bit(which is 1) including the rightmost set bit.
        int count = 0;
        while (val > 0) {
            val = val & (val - 1);
            count++;
        }
        System.out.println(count);

    }

    public static void main(String[] args) {
        int val = 11;
        findSetBits(val);
        findSetBits2(val);
    }
}
