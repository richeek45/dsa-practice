package bitManipulation;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class NonRepeatingElements {
    static void find2NonRepeatingIntegers(int[] arr) {
        // Find the two non-repeating elements in an array of repeating elements/ Unique Numbers 2
        int N = arr.length;
        int xor = 0;
        int rightSetBit = 0;
        int x = 0, y = 0;
        // this will give the xor of 2 non repeating values x ^ y
        for (int i = 0; i < N; i++) {
            xor = xor ^ arr[i];
        }
        rightSetBit = xor & ~(xor - 1);

        for (int i = 0; i < N; i++) {
            if ((rightSetBit & arr[i]) != 0) {
                x = x ^ arr[i];
            } else {
                y = y ^ arr[i];
            }
        }
        System.out.println(x + ", " + y);

    }

    static void find2NonRepeatingIntegers2(int[] arr) {
        // Using set datastructure
        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            if (set.contains(arr[i])) {
                set.remove(arr[i]);
            } else {
                set.add(arr[i]);
            }
        }

        Iterator<Integer> itr = set.iterator();
        System.out.println(itr.next() + ", " + itr.next());
    }

    public static void main(String[] args) {
        int[] arr = { 2, 3, 7, 9, 11, 2, 3, 11 };
        find2NonRepeatingIntegers(arr);
        find2NonRepeatingIntegers2(arr);
    }
}
