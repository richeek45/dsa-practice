package strings;
// Find all the positions of the first character of the original string in the string to be checked.
// For every position found, consider it to be the starting index of the string to be checked.
// Beginning from the new starting index, compare both strings and check whether they are equal or not.
// (Suppose the original string to is s1, string to be checked to be s2,n is the length of strings and j is the position of the first character of s1 in s2,
//  then for i < (length of original string) , check if s1[i]==s2[(j+1)%n). Return false if any character mismatch is found, else return true.
// Repeat 3rd step for all positions found. i = 0, j = 2
// s1 = "abcd" s2 = "cdab"

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class StringRotation {

    static boolean checkStringRotation(String s1, String s2, int indexFound) {
        // O(N^2)
        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();
        int len1 = ch1.length, len2 = ch2.length;
        for (int i = 0; i < len1; i++) {
            if (ch1[i] != ch2[(indexFound + i) % len2]) {
                return false;
            }
        }
        return true;
    }

    static void findCharacterIndexes(String s1, String s2) {
        // store all the instances of char[0] of s1 in arraylist
        ArrayList<Integer> indexes = new ArrayList<>();
        boolean isRotation = false;
        char firstChar = s1.charAt(0);
        int len1 = s1.length(), len2 = s2.length();
        if (len1 == len2) {
            // search all the instances of firstChar in s2
            for (int i = 0; i < len1; i++) {
                if (s2.charAt(i) == firstChar) {
                    indexes.add(i);
                }
            }

            for (Integer idx : indexes) {
                isRotation = checkStringRotation(s1, s2, idx);
                if (isRotation) {
                    break;
                }
            }

        }
        System.out.println("Rotation String: " + isRotation);
    }

    static void checkStringRotationUsingQueue(String s1, String s2) {
        boolean isRotation = false;
        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();
        int len1 = ch1.length, len2 = ch2.length;
        Queue<Character> q1 = new LinkedList<Character>();
        Queue<Character> q2 = new LinkedList<Character>();
        // add the characters to the queue
        for (int i = 0; i < len1; i++) {
            q1.add(ch1[i]);
        }
        for (int i = 0; i < len2; i++) {
            q2.add(ch2[i]);
        }

        while (len2 > 0) {
            len2--;
            char ch = q2.peek();
            q2.remove();
            q2.add(ch);
            if (q2.equals(q1)) {
                isRotation = true;
            }

        }
        System.out.println(isRotation);

    }

    static void checkStringRotation3(String s1, String s2) {
        // str1 = “ABACD”, str2 = “CDABA”
        // temp = str1.str1 = “ABACDABACD”
        // Since str2 is a substring of temp, str1 and str2 are rotations of each other.
        boolean isRotation = false;
        if (s1.length() == s2.length() && (s1 + s2).contains(s1)) {
            isRotation = true;
        }
        System.out.println(isRotation);
    }

    static void checkStringRotationPrefixSuffixCompare(String s1, String s2) {
        // O(N)
        int len1 = s1.length(), len2 = s2.length();
        boolean isRotation = false;
        if (len1 != len2) {
            isRotation = false;
        } else {
            for (int i = 0; i < len1; i++) {
                if (s1.charAt(i) == s2.charAt(0)) {
                    // suffix of s1 with prefix of s2 and prefix of s1 with suffix of s2
                    if (s1.substring(i).equals(s2.substring(0, len2 - i)) && s1.substring(0, i).equals(s2.substring(len2 - i))) {
                        isRotation = true;
                    }
                }
            }
        }
        System.out.println(isRotation);
    }
    public static void main(String[] args) {
        String s1 = "abcdefg", s2 = "efgabcd";
//        findCharacterIndexes(s1, s2);
//        checkStringRotationUsingQueue(s1, s2);
//        checkStringRotation3(s1, s2);
        checkStringRotationPrefixSuffixCompare(s1, s2);


    }
}
