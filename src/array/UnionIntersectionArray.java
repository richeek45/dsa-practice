package array;

import java.util.*;

public class UnionIntersectionArray {

    // Using HashedSet dataStructure
    static void getUnionSet (int arr1[], int arr2[], int size1,  int size2) {
        HashSet<Integer> set = new HashSet<>();
        int minSize = size1 < size2 ? size1 : size2;

        for (int i = 0; i < minSize; i++) {
            set.add(arr1[i]);
            set.add(arr2[i]);
        }

        if (size1 > size2) {
            for (int i = minSize; i < size1; i++) {
                set.add(arr1[i]);
            }
        } else if (size2 > size1) {
            for (int i = minSize; i < size2; i++) {
                set.add(arr2[i]);
            }
        }

        System.out.println("Number of elements after union operation " + set.size());
        System.out.println(set.toString());
    }

    static void printIntersection (int arr1[], int arr2[], int size1, int size2) {
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < size1; i++) {
            set.add(arr1[i]);
        }

        System.out.println("Intersection");
        for (int i = 0; i < size2; i++) {
            if (set.contains(arr2[i])) {
                System.out.print(arr2[i] + " ");
            }
        }
    }

    // Using HashMap DataStructure
    static void getUnionMap (int arr1[], int arr2[], int size1, int size2) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < size1; i++) {
            map.put(arr1[i], i);
        }
        for (int i = 0; i < size2; i++) {
            map.put(arr2[i], i);
        }

        for (Map.Entry mapElement : map.entrySet()) {
            System.out.print(mapElement.getKey() + " ");
        }
    }

    // Using sorting without Data Structure
    static int[] getUnion (int arr1[], int arr2[], int size1, int size2) {
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        int result[] = new int[size1 + size2];
        int index = 0, left = 0, right = 0;

        while (left < size1 && right < size2) {
            if (arr1[left] < arr2[right]) {
                if (index == 0 || arr1[left] != result[index - 1]) {
                    result[index] = arr1[left];
                    index++;
                }
                left++;
            } else if (arr1[left] > arr2[right]) {
                if (index == 0 || arr2[right] != result[index - 1]) {
                    result[index] = arr2[right];
                    index++;
                }
                right++;
            }

            while (left < size1) {
                if (index == 0 || arr1[left] != result[index - 1]) {
                    result[index] = arr1[left];
                    index++;
                }
                left++;
            }

            while (right < size2) {
               if (index == 0 || arr2[right] != result[index - 1]) {
                   result[index] = arr2[right];
                   index++;
               }
                right++;
            }
        }
        return result;
    }

    static int[] getIntersection (int arr1[], int arr2[], int size1, int size2) {
        int min = Math.min(size1, size2);
        int result[] = new int[min];
        int index = 0, left = 0, right = 0;

        while (left < size1 && right < size2) {
            if (arr1[left] < arr2[right]) {
                left++;
            } else if (arr2[right] < arr1[left]) {
                right++;
            } else {
                if (index != 0 && arr1[left] == result[index - 1]) {
                    left++;
                    right++;
                } else {
                    result[index] = arr1[left];
                    left++;
                    right++;
                    index++;
                }
            }
        }
        return result;
    }

    static void printArray(int arr[], int size) {
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i] + " ");
        }
    }
    public static void main (String args[]) {
        int arr1[] = { 1, 2, 5, 6, 2, 3, 5, 7, 3 };
        int arr2[] = { 2, 4, 5, 6, 8, 9, 4, 6, 5, 4 };
        int size1 = arr1.length;
        int size2 = arr2.length;
        // getUnionSet(arr1, arr2, size1, size2);
        // getUnionMap(arr1, arr2, size1, size2);
        //  int[] result = getUnion(arr1, arr2, size1, size2);
        int result[] = getIntersection(arr1, arr2, size1, size2);
        printArray(result, result.length);

         // printIntersection(arr1, arr2, size1, size2);

    }
}
