package strings;

import java.util.HashMap;
import java.util.Map;

public class FirstRepeatingWord {
    static void findFirstRepeatingWord(String str) {
        HashMap<String, Integer> map = new HashMap<>();

        String temp = "";
        String ans = "";
        // adding the word in reverse order
        // This loop adds words after encountering a space. First word needs to be checked separately.
        for (int i =  str.length() - 1; i >= 0; i--) {
            // if character of the string is not space then add it to the temp string
            if (str.charAt(i) != ' ') {
                temp += str.charAt(i);
            } else {
                // character is a string, temp is currently one word.
                // Add it to the map
                if (!map.containsKey(temp)) {
                    map.put(temp, 1);
                } else {
                    map.put(temp, map.get(temp) + 1);
                }

                if (map.get(temp) > 1) {
                    ans = temp;
                }
                temp = "";
            }
        }
        // Checking if the first word is added twice
        if (!map.containsKey(temp)) {
            map.put(temp, 1);
        } else {
            map.put(temp, map.get(temp) + 1);
        }
        if (map.get(temp) > 1) {
            ans = temp;
        }

        // reverse the answer
        if (!ans.equals("")) {
            StringBuilder str1 = new StringBuilder();
            str1.append(ans);
            str1.reverse();
            System.out.println(str1);
        }
    }

    static void findFirstRepeatingWord2(String str) {
        String[] token = str.split(" ");
        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0; i < token.length; i++) {
            if (!map.containsKey(token[i])) {
                map.put(token[i], 1);
            } else {
                map.put(token[i], map.get(token[i]) + 1);
            }
        }

        String ans = "";
        for (int i = 0; i < token.length; i++) {
            if (map.get(token[i]) > 1) {
                ans = token[i];
                break;
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
        String str = "Ravi had been saying that he had been there";
        String str1 = "he had had he";
//        findFirstRepeatingWord(str1);
        findFirstRepeatingWord2(str1);
    }
}
