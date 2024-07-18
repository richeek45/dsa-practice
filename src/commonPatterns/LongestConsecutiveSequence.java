package commonPatterns;
// 128. Longest Consecutive Sequence
// int[] nums = [100,4,200,1,3,2], Output: 4, [1, 2, 3, 4]
// Input: nums = [0,3,7,2,5,8,4,6,0,1], 9
// Solution: 1. Convert into set
// 2. Find the start of the sequence by checking if left neighbour is present


import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {


    public static void main(String[] args) {
//        int[] nums = {100,4,200,1,3,2};
        int[] nums = {0,3,7,2,5,8,4,6,0,1};
        Set<Integer> numSet = new HashSet<>();

        for(int num: nums) {
            numSet.add(num);
        }

        int longest = 0;

        for(int n: numSet) {
            int length = 1;
            if(!numSet.contains(n-1)) {
                while(numSet.contains(n + length)) {
                    length++;
                }
            }
            longest = Math.max(longest, length);
        }

        System.out.println(longest);
    }
}
