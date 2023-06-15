package strings;

import java.util.Stack;

public class ReverseString {
    static String reverseString(char[] str) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length; i++) {
            stack.push(str[i]);
        }
        for (int i = 0; i < str.length; i++) {
            str[i] = stack.peek();
            stack.pop();
        }
        return String.valueOf(str);
    }

    static void reverseString1(String str) {
        StringBuffer revString = new StringBuffer();
        for (int i = str.length() - 1; i >= 0; i--) {
            revString.append(str.charAt(i));
        }
        System.out.println(revString);
    }

    static void reverseString2(String str) {
        char ch[] = str.toCharArray();
        int len = str.length();
        for (int i = 0, j = len -1; i < j; i++, j--) {
            char temp = ch[i];
            ch[i] = ch[j];
            ch[j] = temp;
        }
        System.out.println(ch);
    }

    static void swap(char ch[], int i, int j) {
        char temp = ch[i];
        ch[i] = ch[j];
        ch[j] = temp;
    }

    static void recursiveReverse(String str, int i) {
        int len = str.length();
        char ch[] = str.toCharArray();
        // base case
        if (i == len / 2) {
            return;
        }
        swap(str.toCharArray(), i, len - i - 1);
        recursiveReverse(str, i + 1);
    }

    static void reverseStringInBuildFunction(String str) {
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        str = sb.toString();
        System.out.println(str);
    }
    public static void main(String[] args) {
        String str = "radar";
//        str = reverseString(str.toCharArray());
//        reverseString1(str);
//        reverseString2(str);
        recursiveReverse(str, 0);
//        reverseStringInBuildFunction(str);
        System.out.println(String.valueOf(str));
    }
}
