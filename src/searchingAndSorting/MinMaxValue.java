package searchingAndSorting;

public class MinMaxValue {
    static class Pair {
        int min;
        int max;
    }

    static Pair findMinMax(int[] arr, int low, int high) {
        // finding minimum and maximum using tournament method
        Pair minmax = new Pair();
        Pair mml = new Pair();
        Pair mmr = new Pair();
        int mid;
        // in case of odd number of elements when recursion end in one value comparison
        if (low == high) {
            minmax.min = arr[low];
            minmax.max = arr[low];
            return minmax;
        }

        // comparison in pairs
        if (high == low + 1) {
            if (arr[low] > arr[high]) {
                minmax.max = arr[low];
                minmax.min = arr[high];
            } else {
                minmax.max = arr[high];
                minmax.min = arr[low];
            }
            return minmax;
        }

        mid = low +  (high - low) / 2;
        mml = findMinMax(arr, low, mid);
        mmr = findMinMax(arr, mid+1, high);

        if (mml.max > mmr.max) {
            minmax.max = mml.max;
        } else {
            minmax.max = mmr.max;
        }

        if (mml.min < mmr.min) {
            minmax.min = mml.min;
        } else {
            minmax.min = mmr.min;
        }

        return minmax;
    }

    public static void main(String[] args) {
        int[] arr = { 1000, 11, 445, 1, 330, 3000 };
        Pair minmax = findMinMax(arr, 0, arr.length-1);
        System.out.println(minmax.min + " " + minmax.max);
    }
}
