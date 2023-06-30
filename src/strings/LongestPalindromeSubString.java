package strings;

public class LongestPalindromeSubString {
    static int lpsRecursion(String str, int i, int j) {
        if (i == j) {
            return 1;
        }
        boolean charMatch = str.charAt(i) == str.charAt(j);
        if (charMatch && (i + 1) == j) {
            return 2;
        }

        if (charMatch) {
            return 2 + lpsRecursion(str, i + 1, j - 1);
        }
        // character at the indexes does not match
        return Math.max(lpsRecursion(str, i, j + 1), lpsRecursion(str, i + 1, j));
    }

    static int lpsUsingDP(String str) {
        int len = str.length();
        int[][] dp = new int[len][len];
        // i, j  are the subString from index i to index j
        // substring from index (i, i) same string to same string is palindrome of size 1.
        for (int i = 0; i < len; i++) {
            dp[i][i] = 1;
        }

        for (int c = 2; c <= len; c++) {
            for (int i = 0; i < len - c + 1; i++) {
                int j = i + c - 1; // checking the difference of 1 string for c = 2
                // difference of 2 string for c = 3, 3 -> c = 4 (0,4)
                boolean charMatch = str.charAt(i) == str.charAt(j);
                if (charMatch && c == 2) {
                    dp[i][j] = 2;
                } else if (charMatch) {
                    // outside characters match in a substring of (i, j), check if the inner substring is palindrome
                    dp[i][j] = 2 + dp[i+1][j-1];
                } else {
                    // if the substring (i, j) does not match check for maximum substring (i, j-1) and (i+1, j)
                    dp[i][j] = Math.max(dp[i][j-1], dp[i+1][j]);
                }
            }
        }

        return dp[0][len-1];
    }

    static String lpsUsingDP2(String str) {
        int m = str.length();
        boolean[][] dp  = new boolean[m][m];
        int maxLength = 1;
        int start = 0;

        for (int i = 0; i < m; i++) {
            // substring of length 1 of same character is a palindrome
            dp[i][i] = true;
        }

        for (int i = 0; i < m - 1; i++) {
            // check the adjacent substring (i, i+1) and update the starting index of palindrome
            if (str.charAt(i) == str.charAt(i+1) && i < m -1) {
                dp[i][i+1] = true;
                maxLength = 2;
                start = i;
            }
        }

        for (int len = 3; len <= m; ++len) {
            for (int i = 0; i < m - len + 1; ++i) {
                int j = i + len - 1;

                if (str.charAt(i) == str.charAt(j) && dp[i + 1][j - 1]) {
                    // if the characters match, check if the substring [i+1][j-1] is palidrome
                    dp[i][j] = true;
                    if (len > maxLength) {
                        maxLength = len;
                        start = i;
                    }
                }
            }
        }
        return str.substring(start, start + maxLength);
    }

    public static void main(String[] args) {
        String str = "forgeeksskeegfor";
        String str2 = "ABBDCACB";

//        lpsRecursion(str, 0, str.length() - 1);
        String subString = lpsUsingDP2(str);
        System.out.println(subString);
    }
}
