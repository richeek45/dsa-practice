package strings;

import java.util.ArrayList;
import java.util.List;

public class PrintSubsequences {
    static void printSubsequence(String str) {
        generateSubsequence(str, " ", 0);
    }

    static void generateSubsequence(String str, String sub, int index) {
        if (index == str.length()) {
            System.out.print(sub + ", ");
            return;
        }
        generateSubsequence(str, sub, index + 1);
        generateSubsequence(str, sub + str.charAt(index), index + 1);
    }

    static String printSubsets(String s, int index) {
        int j = 0; // indicates which character to add to the substring
        String sub = "";
        // if the lsb of index = 1 add the char to the sub, else right shift to process the next bit
        // and continue to check if lsb = 1.
        while (index > 0) {
            if ((index & 1) == 1) {
                sub += s.charAt(j);
            }
            j++;
            index = index >> 1;
        }
        return sub;
    }
    static List<String> createSubsets(String s) {
        List<String> res = new ArrayList<String>();
        // iterating with possible variations with left shift operator
        // each time we create a subsequence for corresponding binary representation
        for (int i = 0; i < (1 << s.length()); i++) {
            res.add(printSubsets(s, i));
        }
        return res;
    }
    static void printSubSequence1(String str) {
        List<String> print = createSubsets(str);
        for (String sub: print) {
            System.out.print(sub + " ");
        }
    }

    static void printSubRecursion(String str, int index, String curr) {
        int len = str.length();

        if (index == len) {
            return;
        }
        if (curr != null && !curr.trim().isEmpty()) {
            System.out.print(curr + " ");
        }
        for (int i = index + 1; i < len; i++) {
            curr += str.charAt(i);
            printSubRecursion(str, i, curr);
            // backtracking
            curr = curr.substring(0, curr.length() - 1);
        }
    }

    static void printSubsequence2(String str) {
        int index = -1;
        String curr = "";
        printSubRecursion(str, index, curr);
    }
    public static void main(String[] args) {
        String s1 = "abc";
//        printSubsequence(s1);
//        printSubSequence1(s1);
        printSubsequence2(s1);
    }
}
