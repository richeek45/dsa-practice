package commonPatterns;
// Input: s1 = "ab", s2 = "eidbaooo", true
// s1 = "ab", s2 = "eidboaoo", false


public class PermutationString {

    public static boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        int[] s1Count = new int[26];
        int[] s2Count = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            s1Count[s1.charAt(i) - 'a']++;
            s2Count[s2.charAt(i) - 'a']++;
        }

        int matches = 0, l = 0;
        for (int i = 0; i < 26; i++) {
            if (s1Count[i] == s2Count[i]) {
                matches++;
            }
        }

        for (int i = s1.length(); i < s2.length(); i++) {
            if (matches == 26) {
                return true;
            }

            int index = s2.charAt(i) - 'a';
            s2Count[index]++;
            if (s1Count[index] == s2Count[index]) {
                matches++;
            } else if (s1Count[index] + 1 == s2Count[index]) {
                matches--;
            }

            index = s2.charAt(l) - 'a';
            s2Count[index]--;

            if (s1Count[index] - 1 == s2Count[index]) {
                matches--;
            } else if (s1Count[index] == s2Count[index]) {
                matches++;
            }
            l++;
        }

        return matches == 26;
    }

    public static void main(String[] args) {
//        String s1 = "ab", s2 = "eidbaooo";
        String  s1 = "ab", s2 = "eidboaoo";
        boolean check = checkInclusion(s1, s2);
        System.out.println(check);
    }
}
