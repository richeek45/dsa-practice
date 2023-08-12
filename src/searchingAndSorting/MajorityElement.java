package searchingAndSorting;

import java.util.Arrays;
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

    static void findMajorityElement3(int[] arr) {
        // Using sorting method
        Arrays.sort(arr);
        int count = 1, index = 0;
        int N = arr.length;
        for (int i = 1; i < N; i++) {
            if (arr[index] == arr[i]) {
                count++;
            } else {
                count = 1;
                index = i;
            }
        }
        if (count > N /2) {
            System.out.println(arr[index]);
        } else {
            System.out.println("Not found");
        }
    }

    private static int countOccurences(int[] arr, int val) {
        int N = arr.length;
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (arr[i] == val) {
                count++;
            }
        }
        return count;
    }

    private static int findMajorityUtil(int[] arr, int low, int high) {
        // {4, 4, 4, 3, 3, 4, 4, 3};
        if (low == high) {
            return arr[low];
        }

        int mid = low + (high - low) / 2;
        int leftMajority = findMajorityUtil(arr, low, mid);
        int rightMajority = findMajorityUtil(arr, mid+1, high);
        // majority element in the left is same as right
        if (leftMajority == rightMajority) {
            return leftMajority;
        }

        // count the occurences of the majority elements in the entire array
        int leftCount = countOccurences(arr, leftMajority);
        int rightCount = countOccurences(arr, rightMajority);
        if (leftCount > (high - low + 1) / 2 ) {
            return leftMajority;
        }
        if (rightCount > (high - low + 1) / 2) {
            return rightMajority;
        }

        // NO majority element is found
        return -1;
    }

    static void findMajorityElement4(int[] arr) {
        // Using Divide and Conquer
        int majorityElement = findMajorityUtil(arr, 0, arr.length-1);
        System.out.println(majorityElement);
    }

    static class Node {
        int count;
        int val;
        Node left;
        Node right;
    }

    // global variable for storing maxCount
    static int majorCount = 0;

    static Node newNode(int val) {
        Node temp = new Node();
        temp.count = 1;
        temp.val = val;
        temp.left = null;
        temp.right = null;
        return temp;
    }
    static Node insert(Node root, int key) {
        if (root == null) {
            majorCount = Math.max(majorCount, 1);
            return newNode(key);
        }

        if (key < root.val) {
            root.left = insert(root.left, key);
        } else if (key > root.val) {
            root.right = insert(root.right, key);
        } else {
            root.count += 1;
        }

        // find the majority count
        majorCount = Math.max(majorCount, root.count);

        return root;
    }

    static void inOrderTraversal(Node root, int count) {
        if (root != null) {
            inOrderTraversal(root.left, count);
            if (root.count == count) {
                System.out.println(root.val);
            }
            inOrderTraversal(root.right, count);
        }
    }
    static void findMajorityElement5(int[] arr) {
        // Using BST
        // Every time a same element is encountered we increase the count by 1
        int N = arr.length;
        Node root = newNode(arr[0]);
        for (int i = 1; i < N; i++) {
            insert(root, arr[i]);
        }

        if (majorCount > (N / 2)) {
            inOrderTraversal(root, majorCount);
        }

    }

    public static void main(String[] args) {
        int[] arr = {4, 4, 4, 3, 3, 4, 4, 3};
        int[] arr1 = { 1, 3, 3, 1, 2 };
        int[] arr2 = {1, 3, 3, 3, 2};

//        findMajorityElement(arr);
//        findMajorityElement2(arr);
//        findMajorityElement3(arr);
//        findMajorityElement4(arr2);
        findMajorityElement5(arr);
    }

}
