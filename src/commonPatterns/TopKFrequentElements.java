package commonPatterns;
// Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
// nums = [1,1,1,2,2,3], k = 2, Output: [1,2]
// nums = [1], k = 1, Output: [1]

import java.util.*;

public class TopKFrequentElements {

    public  static int[] topKFrequent(int[] nums, int k) {
        List<Integer>[] freq = new List[nums.length+1];
        Map<Integer, Integer> count = new HashMap<>();

        for(int num: nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        for (int i = 0; i < freq.length; i++) {
            freq[i] = new ArrayList<>();
        }

        for (Map.Entry<Integer, Integer> entry: count.entrySet()) {
            freq[entry.getValue()].add(entry.getKey());
        }

        int[] res = new int[k];
        int index = 0;
        for (int i = freq.length - 1; i > 0 && index < k; i--) {
            for(Object n: freq[i]) {
                res[index++] = (int) n;

                if (index == k) {
                    return res;
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3};
        int k = 2;

        int[] res = topKFrequent(nums, k);
        System.out.println(Arrays.toString(res));
    }
}
