package commonPatterns;

import java.util.ArrayList;
import java.util.List;

public class EncodeDecode {
    public static void main(String[] args) {
        String[] str = {"neet","code","love","you"};
        Solution sol = new Solution();
        String encoded = sol.encode(str);
        System.out.println(encoded);
        List<String> decoded = sol.decode(encoded);
        System.out.println(decoded);
    }
}

class Solution {

    String encode(String[] strs) {
        if (strs.length == 0) return "";
        StringBuilder res = new StringBuilder();

        for (String s: strs) {
            res.append(s.length()).append(s);
        }

        return res.toString();
    }

    ArrayList<String> decode(String str) {
        ArrayList<String> res = new ArrayList<>();
        int i = 0, n=str.length();
        while(i < n) {
            int size = Character.getNumericValue(str.charAt(i++));;
            res.add(str.substring(i, i+size));
            i += size;
        }

        return res;
    }
}