package backtracking;


import java.util.ArrayList;
import java.util.List;

public class PermutationString {
    private static List<String> result = new ArrayList<>();

    private static String swap(String str, int i, int j) {
        char temp;
        char[] charArray = str.toCharArray();
        temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }

    static void findAllPermutations(String str, int left, int right) {
        if(left == right) {
            result.add(str);
            return;
        }

        for(int i = left; i <= right; i++) {
            str = swap(str, left, i);
            findAllPermutations(str, left+1, right);
        }
    }


    public static void main(String[] args) {
        String str = "ABC";
        findAllPermutations(str, 0, str.length()-1);
        System.out.println(result);
    }
}
