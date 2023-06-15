package strings;

public class PalindromeString {

    static void checkPalindromeString(String str) {
        int len = str.length();
        boolean isPalindrome = true;
        for (int i = 0; i < len / 2; i++) {
            if (str.charAt(i) != str.charAt(len - i - 1)) {
                isPalindrome = false;
            }
        }
        System.out.println(isPalindrome);
    }


    public static void main (String[] args) {
        String str = "level";
        checkPalindromeString(str);
    }
}
