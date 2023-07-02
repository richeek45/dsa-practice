package strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PrintPermutationString {

    private static String swap (String s, int i, int j) {
        char[] ch = s.toCharArray();
        char temp = ch[i];
        ch[i] = ch[j];
        ch[j] = temp;
        return String.valueOf(ch);
    }
    static void printPermutations(String str, int l, int r) {
        int len = str.length();

        if (l == r) {
            System.out.print(str + " ");
            return;
        }

        for(int i = l; i <= r; i++) {
            str = swap(str, l, i);
            printPermutations(str, l + 1, r);
//            str = swap(str, l, i);
        }
    }

    static void printPermutations2(String str, String ans, ArrayList<String> dp) {


        if (str.length() == 0) {
            if (!dp.contains(ans)) {
                System.out.print(ans + " ");
                dp.add(ans);
            }
            return;
        }

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            // removing the character at index i
            String leftSubstring = str.substring(0, i);
            String rightSubstring = str.substring(i+1);
            String rest = leftSubstring + rightSubstring;
            // append it to the end of the ans string
            printPermutations2(rest, ans + ch, dp);
        }
    }

    private static Map<Character, Integer> buildFrequencyMap(String str) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char ch: str.toCharArray()) {
            frequencyMap.put(ch, frequencyMap.getOrDefault(ch, 0) + 1);
        }
        return frequencyMap;
    }

    private static void printDistinctPermutationsHelper(char[] uniqueChars, int[] count, char[] permutations, int level) {
        // level is for storing the length of the string added to the permutation
        if (level == permutations.length) {
            System.out.print(new String(permutations) + " ");
            return;
        }

        for (int i = 0; i < uniqueChars.length; i++) {
            if (count[i] == 0) {
                // the character count is decremented to 0. Skip the loop and return the previous function call
                continue;
            }

            permutations[level] = uniqueChars[i];
            count[i]--;
            printDistinctPermutationsHelper(uniqueChars, count, permutations, level + 1);
            // incrementing the count to 1 from 0 after returning from the previous continue;
            count[i]++;
        }
    }

    static void printDistinctPermutations(String str) {
        Map<Character, Integer> frequencyMap = buildFrequencyMap(str);
        char[] uniqueChar = new char[frequencyMap.size()];
        int[] count = new int[frequencyMap.size()];
        int index = 0;
        for (Map.Entry<Character, Integer> map: frequencyMap.entrySet()) {
            uniqueChar[index] = map.getKey();
            count[index] = map.getValue();
            index++;
        }
        char[] permutations = new char[str.length()];
        printDistinctPermutationsHelper(uniqueChar, count, permutations, 0);
    }


    public static void main (String[] args) {
        String str = "AAB";
        ArrayList<String> dp = new ArrayList<String>();
//        printPermutations(str, 0, str.length() - 1);
//        printPermutations2(str, "", dp);
        printDistinctPermutations(str);
    }
}
