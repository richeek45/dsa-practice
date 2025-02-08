package array;

import java.util.Arrays;

public class SmallestMissingPositiveNum {
    static void missingPositiveNumber(int[] arr) {
        int len = arr.length;

        Arrays.sort(arr);
        int res = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == res) {
                res++;
            } else if (arr[i] > res) {
                break;
            }
        }
        System.out.println(res);
    }

    static void missingPositiveNumber1(int[] arr) {
        int len = arr.length;
        int[] visited = new int[len+1];
        Arrays.fill(visited, 0);

        for (int i = 0; i < len; i++) {
            if (arr[i] > 0 && arr[i] < len) {
                visited[arr[i]] = 1;
            }
        }
        int res = 0;
        for (int i = 1; i <= len; i++) {
            if (visited[i] == 0) {
                res = i;
                break;
            }
        }

        System.out.println(res);
    }

    static void missingPositiveNumber2(int[] arr) {
        int len = arr.length;
        // arrange the number to its correct position
        // check arr[i], if not i+1, then iterate and searcb i+1 and swap both the values
        // for arr[i], we check if the value is arr[i] = i + 1,
        // if not then we put the value in its correct position
        // if arr[i] = 4, it should be in arr[i]-1= 4-1=3 index
        // i.e. arr[i] should be in arr[arr[i] - 1]
        // so arr[arr[i]-1] should be swapped with arr[i] at i position
        // we need while here because after swapping, if the arr[i] is still misplaced then
        // we need to swap again to its correct position

        for (int i = 0; i < len; i++) {
            while ((arr[i] >= 1 && arr[i] <= len) && (arr[i] != arr[arr[i] - 1])) {
                int temp = arr[i];
                arr[i] = arr[arr[i]-1];
                arr[temp-1] = temp;
            }
        }
        int res = 0;
        // to check the first number not present in array
        for (int i = 1; i <= len; i++) {
            if (arr[i-1] != i) {
                res = i;
                break;
            }
        }
        System.out.println(res);
    }

    static void missingPositiveNumber3(int[] arr) {
        // 1. moving all the positive values on the left side
        // 2. iterating over the elements and using the value as indices like arr[i]-1 = index
        // and negating the value at that index
        int len = arr.length;
        int pivotIndex = 0; // lastIndex+1 for all the positive values on the left side
        for (int i = 0; i < len; i++) {
            if (arr[i] > 0) {
                // swap with pivotINdex and increment
                int temp = arr[pivotIndex];
                arr[pivotIndex] = arr[i];
                arr[i] = temp;
                pivotIndex++;
            }
        }

        // iterating through the positive values
        for (int i = 0; i < pivotIndex; i++) {
            // take a value go to that index and negate the value present in that index
            // -ve value at any index determines that value is present,
            // here we are considering the index as the value present

            int val = Math.abs(arr[i]); // we need this because the value can be made -ve by other values
            if (val-1 < pivotIndex && arr[val-1] > 0) {
                arr[val-1] = -arr[val-1];
            }
        }

        int res = 0;
        for (int i = 0; i < pivotIndex; i++) {
            if (arr[i] > 0) {
                res = i+1;
                break;
            }
        }
        System.out.println(res);
    }

    static void missingPositiveNumber4(int[] arr) {
        // it marks the indices outside of [1, n] range as 1, then make the change
        int n = arr.length;
        int res = 0;
        boolean flag = false;
        for (int i = 0; i < n; i++) {
            if (arr[i] == 1) {
                res = 1;
                flag = true;
                break;
            }
        }

        if (!flag) {
            System.out.println(res);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (arr[i] < 0 || arr[i] > n) {
                arr[i] = 1;
            }
        }

        for (int i = 0; i < n; i++) {
            // out of index -> circular index
            arr[(arr[i]-1) % n] += n;
        }

        for (int i = 0; i < n; i++) {
            if (arr[i] < n) {
                res = i + 1;
                break;
            }
        }

        System.out.println(res);
    }

    public static void main(String[] args) {
        int[] arr = {2, -3, 4, 1, 1, 7};
        int[] arr2 = {3, 4, -1, 1};
//        missingPositiveNumber(arr);
//        missingPositiveNumber1(arr);
//        missingPositiveNumber2(arr);
//        missingPositiveNumber3(arr);
        missingPositiveNumber4(arr);
    }
}
