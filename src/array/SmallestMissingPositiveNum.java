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

    public static void main(String[] args) {
        int[] arr = {2, -3, 4, 1, 1, 7};
        int[] arr2 = {3, 4, -1, 1};
//        missingPositiveNumber(arr);
//        missingPositiveNumber1(arr);
        missingPositiveNumber2(arr);
    }
}
