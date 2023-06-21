package strings;

import java.util.Arrays;

public class ShuffledSubString {
    static String sort(String s) {
        char[] ch = s.toCharArray();
        Arrays.sort(ch);
        return String.valueOf(ch);
    }
    static void findShuffledSubString(String str1, String str2) {
        // O(m*n*log(n)), where n = length of string str1 and m = length of string str2
        // Auxiliary Space: O(n)
        boolean containsString = false;
        int len1 = str1.length(), len2 = str2.length();
        if (len1 > len2) {
            System.out.println("substring not present" + containsString);
            return;
        }

        String str1Copy = sort(str1);
        for (int i = 0; i < len2; i++) {

            if ((i + len1 - 1) >= len2) {
                containsString = false;
                return;
            }
            String str2Copy = "";
            for (int j = 0; j < len1; j++) {
                str2Copy += str2.charAt(i + j);
            }
            str2Copy = sort(str2Copy);
            if (str1Copy.equals(str2Copy)) {
                containsString = true;
            }
            System.out.println(str1Copy + " " + str2Copy + " " + containsString);
        }


    }

    static boolean compare(int[] arr1, int[] arr2) {
        for (int i = 0; i < 256; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }
    static void findShuffledSubString2(String str1, String str2) {
        // O(M + (N-M)*256)
        // using ASCII code
        boolean containsString = false;
        int len1 = str1.length(), len2 = str2.length(); // len1 < len2
        int[] countS1 = new int[256];
        int[] countS2W = new int[256]; // window of length len1 of str2 to compare with str1

        for (int i = 0; i < 256; i++) {
            countS1[i] = 0;
            countS2W[i] = 0;
        }
        // for len1 adding the ASCII char values filled
        for (int i = 0; i < len1; i++) {
            countS1[str1.charAt(i)]++;
            countS2W[str2.charAt(i)]++;
        }
        // comparing the window of len1 for the rest and comparing in each iteration
        for (int i = len1; i < len2; i++) {
            if (compare(countS1, countS2W)) {
                containsString = true;
            }

            // if the window doesn't contain the substring, add a new character and remove the first character
            countS2W[str2.charAt(i)]++;
            countS2W[str2.charAt(i - len1)]--;
        }
        System.out.println(containsString);
    }

    public static void main (String[] args) {
        String str1 = "onetwofour", str2 = "hellotwoonefourworld";
        String str3 = "ABCD", str4 = "BACDGABCDA";

        findShuffledSubString2(str3, str4);
    }
}
