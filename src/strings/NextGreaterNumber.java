package strings;

import java.util.Arrays;

public class NextGreaterNumber {
    private static void swap(int[] digits, int i, int j) {
        int temp = digits[i];
        digits[i] = digits[j];
        digits[j] = temp;
    }
    static void findNextGreaterNumber(int[] digits, int len) {
        // O (N)
        int indexFound = -1;
        // Find the smallest digit in the reverse order of digits
        for (int i = len - 2; i >=0; i--) {
            if (digits[i] < digits[i+1]) {
                indexFound = i;
                break;
            }
        }

        // No smaller digit is found in reverse order. Digits is arranged in descending order
        if (indexFound == -1) {
            System.out.println("Not possible");
            return;
        }

        // find the smallest digit in the array after the indexFound and swap it with the indexFound
        int min = indexFound + 1;
        for (int i = indexFound + 1; i < len; i++) {
            if (digits[i] > digits[indexFound] && digits[i] < digits[min]) {
                min = i;
            }
        }

        swap(digits, indexFound, min);
        // after swapping the next large digit is in place and the digits after indexFound are in descending order
        // arrange in ascending order to get the smallest number which is the next large number
        int i = indexFound + 1, j = len - 1;
        while (i <= j) {
            swap(digits, i++, j--);
        }

        for (int index = 0; index < len; index++)
            System.out.print(digits[index]);
    }


    public static void main(String[] args) {
        int[] digits = { 5,3,4,9,7,6 };
        int len = digits.length;
        findNextGreaterNumber(digits, len);

    }
}
