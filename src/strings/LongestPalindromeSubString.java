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

    static void lps3(String str) {
        //Maintain a variable maxLength = 1 (for storing LPS length) and start =0 (for storing starting index of LPS ).
        //The idea is very simple, we will traverse through the entire string with i=0 to i<(length of string).
        //while traversing, initialize low and high pointer such that low= i-1 and high= i+1.
        //keep incrementing ‘high’ until str[high]==str[i] .
        //similarly keep decrementing ‘low’ until str[low]==str[i].
        //finally we will keep incrementing ‘high’ and decrementing ‘low’ until str[low]==str[high].
        //calculate length=high-low-1, if length > maxLength then maxLength = length and start = low+1 .
        int maxLength = 1, start = 0;
        int len = str.length();
        int high = len, low = 0;

        for (int i = 0; i < len; i++) {
            high = i+1;
            low = i-1;

            // increasing high index until character at index i and high matches
            while (high < len && str.charAt(i) == str.charAt(high)) {
                high++;
            }
            // decreasing low index until character at index i and low index matches
            while (low >= 0 && str.charAt(i) == str.charAt(low)) {
                low--;
            }
            // taking index at i as center of the palindrome check the adjacent characters
            // if characters match continuously check low-- and high++ characters until they do not match
            while (high < len && low >= 0 && str.charAt(high) == str.charAt(low)) {
                high++;
                low--;
            }

            // Currently low and high are out of index of palindrome
            // starts from low+1 and ends in high - 1
            int length = high - low - 1;
            if (maxLength < length) {
                maxLength = length;
                start = low + 1;
            }
        }

        System.out.println(start + " " + maxLength +  " " + str.substring(start, start + maxLength));
    }

    public static void main(String[] args) {
        String str = "forgeeksskeegfor";
        String str2 = "ABBDCACB";

//        lpsRecursion(str, 0, str.length() - 1);
//        String subString = lpsUsingDP2(str);
//        System.out.println(subString);
        lps3(str);
    }
}
