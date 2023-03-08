package array;

public class MaxMin {

    static class Pair {
        int min;
        int max;
    }

    static Pair getMinMax (int arr[], int size) {
        Pair minMax = new Pair();
        int maxValue = minMax.max;
        int minValue = minMax.min;

        // case 1: if the size == 1
        if (size == 1) {
            minValue = arr[0];
            maxValue = arr[0];
            return minMax;
        }

        // case 2: size > 2
        if (arr[0] > arr[1]) {
            maxValue = arr[0];
            minValue = arr[1];
        } else {
            minValue = arr[0];
            maxValue = arr[1];
        }

        for (int i = 2; i < size; i++) {
            if (arr[i] < minValue) {
                minValue = arr[i];
            } else if (arr[i] > maxValue) {
                maxValue = arr[i];
            }
        }
        minMax.min = minValue;
        minMax.max = maxValue;
        return minMax;

    }


    public static void main (String args[]) {
        int array[] = {13, 15, 3, 21, 17, 49, 29};
        int size = array.length;
        Pair minMax = getMinMax(array, size);
        int maxValue = minMax.max;
        int minValue = minMax.min;
        System.out.println("Max Value: " + maxValue + ", Min Value: " + minValue);
    }
}
