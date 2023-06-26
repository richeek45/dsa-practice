package strings;

import java.util.ArrayList;
import java.util.List;

public class LongestCommonSubSequence {
    // S1 = “ABCDGH”, S2 = “AEDFHR” ans = 3 "ADH" O(n * 2^n)
    static String findLCS(String str1, String str2) {
        List<String> subsequences1 = generateSubsequence(str1);
        List<String> subsequences2 = generateSubsequence(str2);

        String lcs = "";
        for (String subsequence1: subsequences1) {
            for (String subsequence2: subsequences2) {
                if (subsequence1.equals(subsequence2) && subsequence1.length() > lcs.length()) {
                    lcs = subsequence1;
                }
            }
        }
        return lcs;
    }

    static List<String> generateSubsequence(String str) {
        // generate all the subsequences
        List<String> subsequences = new ArrayList<>();
        generateSubsequenceHelper(str, "", 0, subsequences);
        return subsequences;
    }

    static void generateSubsequenceHelper(String str, String subsequence, int index, List<String> subsequences) {
        // generate all the subsequences through recursion str = "ABCDGH"
        if (index == str.length()) {
            subsequences.add(subsequence);
            return;
        }
        generateSubsequenceHelper(str, subsequence, index + 1, subsequences);
        generateSubsequenceHelper(str, subsequence + str.charAt(index), index + 1, subsequences);

    }

    static int findLcs1(String str1, String str2, int index1, int index2) {
        if (index1 == 0 || index2 == 0) {
            return 0;
        } else if (str1.charAt(index1 - 1) == str2.charAt(index2 - 1)) {
            return (1 + findLcs1(str1, str2, index1 - 1, index2 - 1));
        } else {
            return Math.max(findLcs1(str1, str2, index1, index2 - 1), findLcs1(str1, str2, index1 - 1, index2));
        }
    }
    public static void main (String[] args) {
        String S1 = "ABCDGH", S2 = "AEDFHR";
        String str1 = "AGGTAB", str2 = "GXTXAYB";
//        String res = findLCS(str1, str2);
        int res = findLcs1(str1, str2, str1.length(), str2.length());
        System.out.println(res);
    }
}
