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

    static void findMajorityElement2(int[] arr) {
        // Boyer-Moore Majority Vote Algorithm:
        // ** This algorithm assumes there is an element which is more than half times in the array
        //Loop through each element and maintains a count of the majority element, and a majority index, maj_index
        //If the next element is the same then increment the count if the next element is not the same then decrement the count.
        //if the count reaches 0 then change the maj_index to the current element and set the count again to 1.
        //Now again traverse through the array and find the count of the majority element found.
        //If the count is greater than half the size of the array, print the element
        //Else print that there is no majority element
        int count = 0, majIndex = 0;
        int N = arr.length;
        for (int i = 1; i < N; i++) {
            if (arr[majIndex] == arr[i]) {
                count++;
            } else {
                count--;
            }

            if (count == 0) {
                majIndex = i;
                count = 1;
            }

        }

        if(isMajority(arr, N, arr[majIndex])) {
            System.out.println("Majority Element: " + arr[majIndex]);
        } else {
            System.out.println("Majority Element not found!");
        }

    }

    private static boolean isMajority(int[] arr, int N, int val) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (arr[i] == val) {
                count++;
            }
        }
        return (count > N/2);
    }

    public static void main(String[] args) {
        int[] arr = {4, 4, 4, 3, 3, 4, 4, 3};
        int[] arr1 = { 1, 3, 3, 1, 2 };

        findMajorityElement(arr);
        findMajorityElement2(arr);
    }

}
