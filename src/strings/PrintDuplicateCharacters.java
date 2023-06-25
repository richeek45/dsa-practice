package strings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Arrays;

public class PrintDuplicateCharacters {
    static void printDups(String str) {
        //  O(N*log(N))
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char key = str.charAt(i);
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + 1);
            } else {
                map.put(key, 1);
            }
        }

        for (Map.Entry element: map.entrySet()) {
            int value = (int) element.getValue();
            if (value > 1) {
                System.out.println(element);
            }
        }
    }

    static void printDups1(String str) {
        // O(N)
        final int NO_OF_CHARS = 256;
        int[] chars = new int[NO_OF_CHARS];
        for (int i = 0; i < str.length(); i++) {
            chars[str.charAt(i)]++;
        }

        for (int i = 0; i < NO_OF_CHARS; i++) {
            if (chars[i] > 1) {
                System.out.println((char)i + "=" + chars[i]);
            }
        }
    }

    static void printDups2UsingSorting (String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        String sortedStr = String.valueOf(chars);
        int len = str.length();

        for (int i = 0; i < len; i++) {
            int count = 1;
            while (i < len - 1 && sortedStr.charAt(i) == sortedStr.charAt(i + 1)) {
                count++;
                i++;
            }
            if (count > 1) {
                System.out.println(sortedStr.charAt(i) + "=" + count);
            }
        }
    }

    static void printDups3UsingBitwiseOperator(String str) {
        HashSet<Character> duplicates = new HashSet<>();
        int bitVector = 0;

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            int mask = 1 << (ch - 'a');
            if ((bitVector & mask) != 0) {
                duplicates.add(ch);
            } else {
                bitVector = bitVector | mask;
            }
        }

        for (char c: duplicates) {
            System.out.print(c + " ");
        }
    }

    public static void main(String[] args) {
        String s1 = "geeksforgeeks";
//        printDups(s1);
//        printDups1(s1);
//        printDups2UsingSorting(s1);
        printDups3UsingBitwiseOperator(s1);
    }
}
