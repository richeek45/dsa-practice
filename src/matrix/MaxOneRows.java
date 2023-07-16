package matrix;

public class MaxOneRows {
    // Given a boolean 2D array, where each row is sorted. Find the row with the maximum number of 1s.
    static void findRowWithMaxOnes(int[][] matrix) {
        int maxCount = -1;
        int row = -1;
        for (int i = 0; i < matrix.length; i++) {
            int count = 0;
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    count++;
                }
            }
            if (count > maxCount) {
                maxCount = count;
                row = i;
            }
        }
        System.out.println(row);

    }

    private static int findFirstOneIndex(int[] arr, int low, int high) {
        // binary search to find the first 1 index
        if (high >= low) {
            int mid = low + (high - low) / 2;
            if (mid == 0 || arr[mid-1] == 0 && arr[mid] == 1) {
                return mid;
            } else if (arr[mid] == 0) { // first 1 in on the right side of the array
                return findFirstOneIndex(arr, mid+1, high);
            } else { // arr[mid] == 1  and its found on the left side of the array
                return findFirstOneIndex(arr, low, mid);
            }
        }
        return -1;
    }
    static void findRowWithMaxOnes2(int[][] matrix) {
        // since the row is sorted we need to find the first 1 index and calculate the count of 1
        // we can use binary search for finding the first 1 index
        int rowLen = matrix.length;
        int maxOnes = 0;
        int row = -1;
        for (int i = 0; i < rowLen; i++) {
            int firstOneIndex = findFirstOneIndex(matrix[i], 0, rowLen - 1);
            int count = (rowLen - firstOneIndex);
            if (firstOneIndex != -1 && count > maxOnes) {
                maxOnes = count;
                row = i;
            }
        }
        System.out.println(row);
    }

    static void findRowWithMaxOnes3(int[][] matrix) {
        // O m(logn) m -> rows n -> cols
        int rowLen = matrix.length;
        int maxCount = 0;
        int row = -1;
        int firstOneIndex = findFirstOneIndex(matrix[0], 0, rowLen-1);
        for (int i = 1; i < rowLen; i++) {
            if (firstOneIndex > 0 && matrix[i][firstOneIndex-1] == 1) {
                int firstIndex = findFirstOneIndex(matrix[i], 0, firstOneIndex -1);
                int count = rowLen - firstIndex;
                if (count > maxCount && firstIndex != -1) {
                    firstOneIndex = firstIndex;
                    row = i;
                }
            }
        }
        System.out.println(row);
    }

    static void findRowWithMaxOnes4(int[][] matrix) {
        // O( m + n) -> m -> rows, n -> cols
        // traversing from right side of the row, if row below of same firstIndex is 0, then ignore
        // move on to the next row, if 1 then move left until 0 is found.
        int rowLen = matrix.length;
        int count = 0;
        int row = -1, j = matrix[0].length-1;
        for (int i = 0; i < rowLen; i++) {
            while(j >= 0 && matrix[i][j] == 1) {
                count++;
                j--;
                row = i;
            }
        }
        System.out.println(row);
    }

    public static void main(String[] args) {
        int mat[][] = { {0, 0, 0, 1},
                {0, 1, 1, 1},
                {1, 1, 1, 1},
                {0, 0, 0, 0}};

//        findRowWithMaxOnes(mat);
//        findRowWithMaxOnes2(mat);
//        findRowWithMaxOnes3(mat);
        findRowWithMaxOnes4(mat);
    }

}
