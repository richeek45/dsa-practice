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
        // O(n * 2^n)
        if (index1 == 0 || index2 == 0) {
            return 0;
        } else if (str1.charAt(index1 - 1) == str2.charAt(index2 - 1)) {
            return (1 + findLcs1(str1, str2, index1 - 1, index2 - 1));
        } else {
            return Math.max(findLcs1(str1, str2, index1, index2 - 1), findLcs1(str1, str2, index1 - 1, index2));
        }
    }

    // Using memoization
    static int findLcs2(String str1, String str2, int index1, int index2, int[][] dp) {
        if (index1 == 0 || index2 == 0) {
            return 0;
        }
        if (dp[index1][index2] != -1) {
            return dp[index1][index2];
        }
        if (str1.charAt(index1 - 1) == str2.charAt(index2 - 1)) {
            dp[index1][index2] = 1 + findLcs2(str1, str2, index1 - 1, index2 - 1, dp);
            return dp[index1][index2];
        }

        dp[index1][index2] = Math.max(findLcs2(str1, str2, index1 - 1, index2, dp), findLcs2(str1, str2, index1, index2 - 1, dp));
        return dp[index1][index2];
    }
    static void findLcs2UsingMemoization(String str1, String str2) {
        // O(m * n)
        int m = str1.length(), n = str2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                dp[i][j] = -1;
            }
        }
        int res = findLcs2(str1, str2, m, n, dp);
        System.out.println(res);
    }

    static void findLcs3UsingDP(String str1, String str2) {
        int m = str1.length(), n = str2.length();
        int[][] dp = new int[m + 1][n + 1];
        // String index starts with 1 and dp table index starts with 0
        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                }
                else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        System.out.println(dp[m][n]);
    }
    public static void main (String[] args) {
        String S1 = "ABCDGH", S2 = "AEDFHR";
        String str1 = "AGGTAB", str2 = "GXTXAYB";
//        String res = findLCS(str1, str2);
//        int res = findLcs1(str1, str2, str1.length(), str2.length());
//        System.out.println(res);
//        findLcs2UsingMemoization(str1, str2);
        findLcs3UsingDP(str1, str2);
    }
}
