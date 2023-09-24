package Graph;

import java.util.*;

public class WordLadder {
    // Given a dictionary, and two words ‘start’ and ‘target’ (both of same length).
    // Find length of the smallest chain from ‘start’ to ‘target’ if it exists, such that adjacent words
    // in the chain only differ by one character and each word in the chain is a valid word i.e.,
    // it exists in the dictionary.
    // It may be assumed that the ‘target’ word exists in dictionary and length of all dictionary words is same.
    static class Pair {
        String str;
        int distance;
        Pair(String str, int distance) {
            this.str = str;
            this.distance = distance;
        }
    }

    private static boolean isCharAdj(String str1, String str2) {
        int len = str1.length();
        int count = 0;
        for(int i = 0; i < len; i++) {
            if(str1.charAt(i) != str2.charAt(i)) {
                count++;
            }
        }
        // if only 1 char is different at a position then it can be modified
        if (count == 1) {
            return true;
        }
        return false;
    }

    public static int shortestChainLength3(Set<String> dict, String start, String target) {
        // using Bi-directional BFS
        // we will start changing the string and adding it to the dictionary with updated distance
        // from both the sides and find the middle ground where both the strings are equal.
        Queue<Pair> q1 = new LinkedList<>();
        Queue<Pair> q2 = new LinkedList<>();
        HashMap<String, Integer> vis1 = new HashMap<>();
        HashMap<String, Integer> vis2 = new HashMap<>();
        q1.add(new Pair(start, 1));
        q2.add(new Pair(target, 1));
        vis1.put(start, 1);
        vis2.put(target, 1);
        int count = 0; // measuring the times traversed

        while(!q1.isEmpty() && !q2.isEmpty()) {
            count++;
//            System.out.print(count);
            Pair curr1 = q1.poll();
            Pair curr2 = q2.poll();
            String word1 = curr1.str;
            String word2 = curr2.str;


            // iterating through all of set dict to find the adjchar match with the word1
            for (String str1 : dict) {
                if(isCharAdj(word1, str1) && !vis1.containsKey(str1)) {
                    q1.add(new Pair(str1, curr1.distance + 1));
                    vis1.put(str1, curr1.distance + 1);

                    if(str1.equals(target)) {
                        return curr1.distance + 1;
                    }

                    if(vis2.containsKey(str1)) {
                        return curr1.distance + vis2.get(str1);
                    }
                }

            }

            for (String str2 : dict) {
                if(isCharAdj(word2, str2) && !vis2.containsKey(str2)) {
                    q2.add(new Pair(str2, curr2.distance + 1));
                    vis2.put(str2, curr2.distance + 1);

                    if(str2.equals(target)) {
                        return curr2.distance + 1;
                    }

                    if(vis1.containsKey(str2)) {
                        return  curr2.distance + vis2.get(str2);
                    }
                }
            }

        }

        return 0;
    }

    public static int shortestChainLength2(Set<String> dict, String start, String target) {
        // creating the intermediate string -> toon -> *oon, t*on, to*n, too*
        if (start == target) {
            return 0;
        }

        Map<String, Vector<String>> map = new HashMap<>();
        for(int i = 0; i < start.length(); i++) {
            // forming the intermediate string
            String str = start.substring(0, i) + "*" + start.substring(i+1);
            Vector<String> s = map.get(str);
            if (s == null) {
                s = new Vector<>();
            }
            s.add(start);
            map.put(str, s);
        }

        for(String itr : dict) {
            String word = itr;
            for(int i = 0; i < word.length(); i++) {
                String str = word.substring(0, i) + "*" + word.substring(i+1);
                Vector<String> s = map.get(str);
                if (s == null) {
                    s = new Vector<>();
                }
                s.add(word);
                map.put(str, s);
            }
        }

        // visualizing the map
        for(Map.Entry<String, Vector<String>> entry :  map.entrySet()) {
            System.out.println("key = " + entry.getKey() + " value = " + entry.getValue());
        }

        // Perform BFS and push (word, distance) into the queue
        Queue<Pair> q = new LinkedList<>();
        Map<String, Integer> visited = new HashMap<String, Integer>();
        q.add(new Pair(start, 1));
        visited.put(start, 1);

        while(!q.isEmpty()) {
            Pair p = q.peek();
            q.remove();
            String word = p.str;
            int distance = p.distance;
            if (word == target) {
                return distance;
            }

            for(int i = 0; i < word.length(); i++) {
                String str = word.substring(0, i) + "*" + word.substring(i+1);
                Vector<String> vect = map.get(str);
                for(int j = 0; j < vect.size(); j++) {
                    if(!visited.containsKey(vect.get(j))) {
                        visited.put(vect.get(j), 1);
                        q.add(new Pair(vect.get(j), distance + 1));
                    }
                }
            }
        }

        return 0;
    }

    public static int shortestChainLength(Set<String> dict, String start, String target) {
        if (start == target) {
            return 0;
        }

        if(!dict.contains(target)) {
            return 0;
        }

        int level = 0, wordLength = start.length();
        Queue<String> q = new LinkedList<>();
        q.add(start);
        while(!q.isEmpty()) {
            int size = q.size();
            level++;
            for(int i = 0; i < size; i++) {
                char[] word = q.peek().toCharArray(); // first iteration "toon"
                q.remove();
                System.out.print(String.valueOf(word) + " ");
                for(int pos = 0; pos < wordLength; pos++) {
                    char originalChar = word[pos];
                    for(char c = 'a'; c <= 'z'; c++) {
                        word[pos] = c;

                        // already found the target
                        if(String.valueOf(word).equals(target)) {
                            System.out.print(target + " ");
                            return level + 1;
                        }

                        // created word is not a valid word i.e. not exists in the dictionary
                        if(!dict.contains(String.valueOf(word))) {
                            continue;
                        }

                        // remove it from the dictionary and add it as a part of the chain
                        dict.remove(String.valueOf(word));
                        q.add(String.valueOf(word));
                    }
                    word[pos] = originalChar;
                }
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        // O(N² * M), where N is the number of entries originally in the dictionary and M is the size of the string.
        // Auxiliary Space: O(M * N)
        Set<String> dict = new HashSet<>();
        dict.add("poon");
        dict.add("plee");
        dict.add("same");
        dict.add("poie");
        dict.add("plie");
        dict.add("poin");
        dict.add("plea");

        String start = "toon";
        String target = "plea";
//        int chainLength = shortestChainLength(dict, start, target);
//        int chainLength2 = shortestChainLength2(dict, start, target);
        int chainLength3 = shortestChainLength3(dict, start, target);
        System.out.println(chainLength3);
    }
}
