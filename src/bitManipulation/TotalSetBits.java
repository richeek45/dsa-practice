package bitManipulation;

import java.util.Scanner;

public class TotalSetBits {
    static void findSetBits(int N) {
        // 00 01 10 11 -> 2^1 + 1
        // 100 101 110 111 -> 2^2
        // 1000 1001 1010 1011 1100 1101 1110 1111 -> 2^3
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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("enter the val: ");
        int N = sc.nextInt();
        findSetBits(N);
    }
}
