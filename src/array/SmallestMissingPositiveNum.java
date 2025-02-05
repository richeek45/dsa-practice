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

    public static void main(String[] args) {
        int[] arr = {2, -3, 4, 1, 1, 7};
//        missingPositiveNumber(arr);
        missingPositiveNumber1(arr);
    }
}
