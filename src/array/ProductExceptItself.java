package array;

public class ProductExceptItself {
    // Problem Statement: Given an array arr[] of integers,
    // you need to return the product of given array elements except including the element itself.
    //NOTE – You should not use division operator ‘ / ’
    static void productArray(int[] arr) {
        // Here we are going to maintain two prefix and suffix array which is going to multiply index (i-1) and (i+1)
        int N = arr.length;
        int[] prod = new int[N];
        int[] prefix = new int[N];
        int[] suffix = new int[N];
        prefix[0] = 1;
        suffix[N-1] = 1;
        for(int i = 1; i < N; i++) {
            prefix[i] = prefix[i-1] * arr[i-1];
        }

        for(int i = N-2; i >= 0; i--) {
            suffix[i] = suffix[i+1] * arr[i+1];
        }

        for(int i = 0; i < N; i++) {
            prod[i] = prefix[i] * suffix[i];
        }

        for(int i = 0; i < N; i++) {
            System.out.print(prod[i] + " ");
        }
    }

    public static void main(String[] args) {
        int[] arr = {10, 3, 5, 6, 2};
        productArray(arr);
    }
}
