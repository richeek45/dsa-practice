package searchingAndSorting;

import java.util.HashMap;
import java.util.Map;

public class MajorityElement {

    static void findMajorityElement(int[] arr) {
        int n = arr.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        for (Map.Entry<Integer, Integer> val: map.entrySet()) {
            if (val.getValue() > n/2) {
                System.out.println(val.getKey());
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 4, 3, 2, 4, 4, 4, 4};

        findMajorityElement(arr);
    }

}
