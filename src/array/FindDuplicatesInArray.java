package array;
import java.lang.reflect.Array;
import java.util.*;
import java.lang.Integer;

public class FindDuplicatesInArray {

    static void usingHashMap(int arr[], int size) {
        HashMap<Integer,Integer> map = new HashMap<>(size);

        for (int i = 0; i < size; i++) {
            if (map.containsKey(arr[i])) {
                Integer k = map.get(arr[i]);
                map.put(arr[i], k + 1);
            } else {
                map.put(arr[i], 1);
            }
        }
        System.out.println(map);
        for (Map.Entry<Integer, Integer> e: map.entrySet()) {
            if (e.getValue() > 1) {
                System.out.println("Key:" + e.getKey() + "Value:" + e.getValue());
            }
        }
    }

    static void usingAdditionApproach(int arr[], int size) {
        for (int i = 0; i < size; i++) {
            int k = arr[i] % size;
            arr[k] = arr[k] + size;
        }
        // Loop to check if the value is double the size
        for (int i = 0; i < size; i++) {
            if (arr[i] >= size * 2) {
                System.out.print(i + " ");
            }
        }
    }

    static ArrayList<Integer> negativeValueReplacementApproach(int arr[], int size) {
        ArrayList<Integer> res = new ArrayList<>();

        int count = 0; // for handling largest element case
        // Incrementing value by 1 to handle 0 case
        for (int i = 0; i < size; i++) {
            arr[i] += 1;
        }
        for (int i = 0; i < size; i++) {
            int k = arr[i];

            int index = Math.abs(k) > size ? Math.abs(k) / (size + 1) : Math.abs(k);

            if (index == size) {
                count++;
                continue;
            }

            int val = arr[index];
            if (val > size) {
                continue;
            } else if ( val < 0) {
                arr[index] = arr[index] * (size + 1);
                res.add(index);
            } else {
                arr[index] = -arr[index];
            }
        }

        if (count > 1) {
            res.add(size - 1);
        }

        if (res.size() == 0) {
            res.add(-1);
        } else {
            Collections.sort(res);
        }

        return  res;
    }

    public static void main (String args[]) {
        int arr[] =   { 0, 4, 3, 2, 7, 8, 2, 3, 1 };
        int size = arr.length;
//        usingHashMap(arr, size);
//        usingAdditionApproach(arr, size);
        ArrayList<Integer> ans = negativeValueReplacementApproach(arr, size);
        for (Integer i : ans) {
            System.out.print(i + " ");
        }
    }
}
