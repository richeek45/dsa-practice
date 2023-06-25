package strings;

import java.util.HashMap;

public class CountAndSaySequence {
    // 1, 11, 21, 1211, 111221, 312211, 13112221, 1113213211, ....
    static void findSequence(int N) {
        String[] terms = new String[N];
        terms[0] = "1";
        terms[1] = "11";
        String str = "11";
        for (int i = 2; i < N; i++) {
            str += "$";
            int len = str.length();
            String temp = "";
            int count = 1;
            char[] ch = str.toCharArray();
            for (int j = 1; j < len; j++) {
                if (ch[j] != ch[j-1]) {
                    temp += count;
                    temp += ch[j - 1];
                    count = 1;
                } else {
                    count++;
                }
            }
            terms[i] = temp;
            str = temp;
        }
        System.out.println(terms[N - 1]);
    }

    static String generator(String str) {
        String res = "";
        HashMap<Character, Integer> tempCount = new HashMap<>();
        for (int i = 0; i < str.length() + 1; i++) {
            if (i == str.length() || !tempCount.containsKey(str.charAt(i)) && i > 0) {
                res += String.valueOf(tempCount.get(str.charAt(i-1))) + str.charAt(i-1);
                tempCount.clear();
            }

            if (i == str.length()) {
                tempCount.put(null, 1);
            }

            if (i != str.length()) {
                char key = str.charAt(i);
                if (tempCount.containsKey(key)) {
                    tempCount.put(key, tempCount.get(key) + 1);
                } else {
                    tempCount.put(key, 1);
                }
            }
        }
        return res;
    }
    static void findSequenceUsingHashMap (int N) {
        String res = "1";
        for (int i = 1; i < N; i++) {
            res = generator(res);
        }
        System.out.println(res);
    }


    public static void main(String[] args) {
        // find the nth term
//        findSequence(7);
        findSequenceUsingHashMap(7);
    }
}
