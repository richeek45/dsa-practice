package commonPatterns;

import java.util.*;

// 49. Group Anagrams
// Given an array of strings strs, group the anagrams together. You can return the answer in any order.
//An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
// typically using all the original letters exactly once
public class GroupAnagram {

    public static ArrayList<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map =  new HashMap<>();

        for (String str : strs) {
            int[] count = new int[26];
            for (char ch: str.toCharArray()) {
                count[ch - 'a']++;
            }

            String key = Arrays.toString(count);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(str);
        }

        return new ArrayList<>(map.values());
    }


    public static void main(String[] args) {
        String[] strs = new String[]{"eat","tea","tan","ate","nat","bat"};
        ArrayList<List<String>> anagrams = groupAnagrams(strs);
        System.out.println(anagrams);
    }
}
