package commonPatterns;
// 3. Longest Substring Without Repeating Characters
// s = "abcabcbb",Output: 3
//  s = "bbbbb", Output: 1
// s = "pwwkew", Output: 3

import java.util.HashSet;
import java.util.Set;

public class LongestSubstring {

    public static void longestSubString(String str) {
        Set<Character> set = new HashSet<>();
        int l = 0, res = 0; // l -> start index of window

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            while (set.contains(ch)) {
                set.remove(str.charAt(l));
                l += 1;
            }
            set.add(ch);
            res = Math.max(res, i - l + 1);
        }


        System.out.println(res);
    }


    public static void main(String[] args) {
        String str1 = "abcabcbb";
        String str2 = "pwwkew";

        longestSubString(str2);
    }
}
