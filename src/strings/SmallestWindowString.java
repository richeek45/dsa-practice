package strings;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

public class SmallestWindowString {
    // Smallest window that contains all characters of string itself
    static void findSmallestSubstring(String str) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char ch: str.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        int size = Integer.MAX_VALUE;
        int distCharCount = map.size();
        String smallestSubString = "";

        for (int i = 0; i < str.length(); i++) {
            int count = 0; // for storing the distinct characters
            char[] visited = new char[256];
            for (int j = 0; j < 256; j++) {
                visited[j] = 0;
            }
            String subStr = "";
            for (int j = i; j < str.length(); j++) {
                // adding the unique character to the array
                if (visited[str.charAt(j)] == 0) {
                    visited[str.charAt(j)]++;
                    count++;
                }
                subStr += str.charAt(j);
                // if all the distinct characters are found
                if (count == distCharCount) {
                    break;
                }
            }
            // if the subString found is smaller in size which contains all distinct strings then update the size
            if (subStr.length() < size && count == distCharCount) {
                size = subStr.length();
                smallestSubString = subStr;
            }
        }

        System.out.println(smallestSubString + "=" + size);
    }

    static void findSmallestSubstring2(String str) {
        // Sliding window technique
        int distCount = 0;  // count for finding distinct characters
        int len = str.length();
        boolean[] visited = new boolean[256];
        Arrays.fill(visited, false);
        // Making character position in the visited array as true if found
        for (int i = 0; i < len; i++) {
            if (!visited[str.charAt(i)]) {
                visited[str.charAt(i)] = true;
                distCount++;
            }
        }

        int start = 0; // start for updating the starting index if same character is found more than once
        int count = 0; // to count the distinct characters found
        int minLen = Integer.MAX_VALUE; // to store the smallest length of the window
        int startIndex = 0; // to update the startIndex window after every loop
        int[] charCount = new int[256]; // to increment the character index if found
        for (int i = 0; i < len; i++) {
            charCount[str.charAt(i)]++;
            if (charCount[str.charAt(i)] == 1) {
                // if unique character is found then increase the count + 1
                count++;
            }
            // if all the distinct characters are found
            if (count == distCount) {
                // if same character at index start is found more than once in the charCount array
                // then decrease the value at array index and increase the start by 1
                while(charCount[str.charAt(start)] > 1) {
                    if (charCount[str.charAt(start)] > 1) {
                        charCount[str.charAt(start)]--;
                    }
                    start++;
                }
                int lenWindow = i - start + 1;
                // update the window if lenWindow size is smaller than minLen
                if (lenWindow < minLen) {
                    minLen = lenWindow;
                    startIndex = start;
                }
            }
        }
        System.out.println(str.substring(startIndex, startIndex + minLen));
    }

    public static void main(String[] args) {
        String str = "aabcbcdbcadda";
//        findSmallestSubstring(str);
        findSmallestSubstring2(str);
    }
}
