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
        //  O(N*log(N)), where N is the given integer and log(N) time is used for the binary conversion of the number.
        // right-shift of a number is N/2 and left-shift is N*2
        int countBits = 0;
        for (int i = 1; i <= N; i++) {
            countBits += countBitsUtil(i);
        }
        System.out.println(countBits);
    }

    static void findSetBits3(int N) {
        int bitNo = 0;
        int countBits = 0;
        // left-shifting 1 by i bits i.e. multiply by 2 while the number is less than N
        while((1 << bitNo) <= N) {
            boolean k = false; // for flipping the bit value, k=true means the bit is set, incrementing the count
            int change = 1 << bitNo; // alternating bits for bitNo
            // (i = 0, change=1), (i=1, change=2), (i=2, change=4)
            // for bitNo = 0 flip for every other number,
            // for bitNo = 1, flip bits for every two consequetive numbers
            // for bitNo = 2, flip bits for every 4 consequetive numbers
            // iterating through bitNo for all the numbers from 1 to N
            for (int j = 0; j <= N; j++) {
                if (k) {
                    countBits += 1;
                }

                if (change == 1) {
                    k = !k; // flipping the bits
                    change = 1 << bitNo; // resetting the change for alternating
                } else {
                    change--;
                }
            }
            bitNo++;
        }
        System.out.println(countBits);
    }

    private static int findBitCount(int N) {
        int count = 0;
        while(N > 0) {
            count++;
            N = N >> 1;
        }
        return count;
    }

    static void findSetBits4(int N) {
        // for every bit no. the bits are set in alternating pattern mentioned above
        // Here instead of calculating for every no. from 1 to N, we take the no. N and calculate
        // the total set bits from iterating through the bits of N with a formula.
        // for no. N bit no. 0 -> the pattern is set for every alternate = 2^0
        // patternCount = N / 2^0, of them half of them would be set.
        // for additional no.s where pattern is exhausted we would use modulator function to get the remaining set bits
        // mod = (N % patternCount + 1) adding +1 for 0 indexing
        // now we need to determine if the mod bits are set or unset
        // modBits = { 1 * mod, if (patternCount is odd);  0, if (patternCount is even) }
        // totalSetBits = (patternCount / 2) * 2^0 + modBits
        int bitCount = findBitCount(N);
        int currSetBits = 0;
        int totalSetBits = 0;
        int patternCount = 0;
        int addRemaining = 0;
        int mod = 0;

        for(int i = 0; i < bitCount; i++) {
            // Number of times the pattern is alternating
            int alternatingBits = (int) (Math.pow(2, i));
            patternCount = N / alternatingBits;
            mod = (N % patternCount + 1);
            // if patternCount = 1 then the set bits are the bits in the number after subtracting the first pattern bits
            if (patternCount == 1) {
                addRemaining = N - (patternCount * alternatingBits) + 1;
            } else if (patternCount % 2 == 1) {
                // if pattern is odd then sequence goes like 0, 1, 0, hence the remaining bits will be set
                addRemaining = mod;
            } else {
                // for even pattern the sequence is 0, 1, 0, 1, hence remaining bits will be unset
                addRemaining = 0;
            }
            // half the times the pattern is repeated the bits are set with remaining bits coming when the
            // pattern is exhausted
            currSetBits = ((patternCount) / 2) * alternatingBits + addRemaining;
            totalSetBits += currSetBits;
        }
        System.out.println(totalSetBits);
    }

    private static int findLargestPower(int N) {
        int power = 0;
        while ((1 << power) < N) {
            power++;
        }
        return (power-1);
    }

    static int findSetBits5(int N) {
        // https://www.geeksforgeeks.org/count-total-set-bits-in-all-numbers-from-1-to-n/
        // for numbers in form of 2^b-1 like 1, 3, 7, 15 the number of bits even after complementing the numbers
        // remain the same, half the bits are set and the other half unset
        // 2^b-1 in bit representation is 1 << b - 1 -> b = highest power of 2, the number of set bits is b * 2^(b-1).
        if (N <= 1) {
            return 1;
        }

        int power = findLargestPower(N);
        // total set bits which follows the pattern 2^b-1 smaller than N, value = 2^b-1
        int value = (int)(Math.pow(2, power))-1;
        int setBits = power * (int)(Math.pow(2, (power-1)));
        // bits that are leftmost bit for remaining numbers greater than number following the pattern
        int leftMostSetBits = (N - value);
        // recursively calling for remaining rightmost bits for numbers greater than value, -1 for excluding counting 0;
        int remainingRightMostBits = findSetBits5(N - value - 1);

        return (setBits + leftMostSetBits + remainingRightMostBits);
    }

    static void findSetBits6(int N) {
        // Do not understand solution
        int two = 2, ans = 0;
        int n = N;
        while (n != 0) {
            ans += (N / two) * (two >> 1);
            if ((N & (two - 1)) > (two >> 1) - 1)
                ans += (N & (two - 1)) - (two >> 1) + 1;
            two <<= 1;
            n >>= 1;
        }
        System.out.println(ans);
//        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("enter the val: ");
        int N = sc.nextInt();
//        findSetBits4(N);
        System.out.println(findSetBits5(N));
    }
}
